package com.backend.lib.Entity;

import com.backend.lib.Enum.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;

    private String uname;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String mobile;

    private String password;

    private boolean active;

    private Role role;

    public User() {
    }

    public User(String uname, String email, String mobile, String password, boolean active) {
        super();
        this.uname = uname;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.active = active;
//        this.confirmPassword = confirmPassword;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

}