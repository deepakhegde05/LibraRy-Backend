package com.backend.lib.Service;

import com.backend.lib.Dto.AdminDto;
import com.backend.lib.Dto.AdminDto2;
import com.backend.lib.Dto.JwtAuthenticationResponse;
import com.backend.lib.Entity.Admin;
import com.backend.lib.Enum.Role;
import com.backend.lib.Repo.AdminRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    @Autowired
    private AdminRepo adminRepo;

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public JwtAuthenticationResponse login(AdminDto2 adminDto2) {
        System.out.println(adminDto2.getEmail());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDto2.getEmail(), adminDto2.getPassword()));

        var user = adminRepo.findByEmail(adminDto2.getEmail()).orElseThrow(() -> new IllegalArgumentException("Illegal email and password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        System.out.println(jwt);
        return jwtAuthenticationResponse;
    }
    public void updateAdmin(AdminDto adminDto, String email){
        Admin admin = adminRepo.findByEmail(email).get();
        admin.setName(adminDto.getName());
        admin.setEmail(adminDto.getEmail());
        admin.setPassword(adminDto.getPassword());
        admin.setDesignation(adminDto.getDesignation());
        admin.setAddress(adminDto.getAddress());
        admin.setRole(Role.ADMIN);
        adminRepo.save(admin);

    }

    public Optional<Admin> getAdminById(String email){
        return adminRepo.findByEmail(email);
    }
}
