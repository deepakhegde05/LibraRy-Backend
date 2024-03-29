package com.backend.lib.Service;

import com.backend.lib.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {

    @Autowired
    private UserRepo userRepo;
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
