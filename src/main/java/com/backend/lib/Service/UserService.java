package com.backend.lib.Service;

import com.backend.lib.Dto.*;
import com.backend.lib.Entity.User;
import com.backend.lib.Enum.Role;
import com.backend.lib.Repo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public void addUser(User user) {
        User User = new User();
        User.setUname(user.getUname());
        User.setEmail(user.getEmail());
        User.setMobile(user.getMobile());
        User.setPassword(passwordEncoder.encode(user.getPassword()));
        User.setRole(Role.USER);
        User.setActive(true);
        userRepo.save(User);
    }

    public JwtAuthenticationResponse login(UserDto3 userDto3) {
        //now its time for generating and refreshing and validating the token while user logs in
        //need to check in repo whether email and password is there and also must be matched
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto3.getEmail(), userDto3.getPassword()));

        var user = userRepo.findByEmail(userDto3.getEmail()).orElseThrow(() -> new IllegalArgumentException("Illegal email and password"));
        var jwt = jwtService.generateToken(user);
        //after sometime token gets expired, required to refresh the tokens
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        //for refreshing the token first we need to extract the email from exixsting passed token
        //and then from that email extracted we need to check whether the email is present in db or not
        String email = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepo.findByEmail(email).orElseThrow();

        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }


    public Optional<User> findThisUser(String email){
        return userRepo.findByEmail(email);
    }
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    @Transactional
    public Optional<User> deleteUser(String email) {
        return Optional.of(userRepo.deleteByEmail(email).orElseThrow());
    }

    public void updateUser(UserDto userDto, String email){
        User user = userRepo.findByEmail(email).get();
        user.setUname(userDto.getUname());
        user.setEmail(userDto.getEmail());
        user.setMobile(userDto.getMobile());
        user.setActive(true);
        userRepo.save(user);
    }

    public void updateUser(UserDto2 userDto, String email){
        User user = userRepo.findByEmail(email).get();
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setActive(true);
        userRepo.save(user);
    }

    public void updateAllUserDetails(User user, String email){
        User user2 = userRepo.findByEmail(email).get();
        user2.setUname(user.getUname());
        user2.setMobile(user.getMobile());
        user2.setPassword(passwordEncoder.encode(user.getPassword()));
        user2.setActive(true);
        userRepo.save(user2);
    }

    public void deactivateUser(String email) {
        User user = userRepo.findByEmail(email).get();
        user.setActive(false);
        userRepo.save(user);
    }
    public void activateUser(String email) {
        User user = userRepo.findByEmail(email).get();
        user.setActive(true);
        userRepo.save(user);
    }

    public UserDetailsService userDetailsService() {
        //inbuilt interface which has loadUsername method
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                //we passing the whatever the username we got from api to findByEmail method which is in user class
                return userRepo.findByEmail(username)
                        .orElseThrow(()->new UsernameNotFoundException(username +" not found"));
            }
        };
    }


}

