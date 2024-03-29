package com.backend.lib.Controller;

import com.backend.lib.Dto.AdminDto;
import com.backend.lib.Dto.AdminDto2;
import com.backend.lib.Dto.JwtAuthenticationResponse;
import com.backend.lib.Dto.UserDto;
import com.backend.lib.Entity.Admin;
import com.backend.lib.Entity.User;
import com.backend.lib.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping("/admin-login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody AdminDto2 adminDto2 ) {
        return ResponseEntity.ok(adminService.login(adminDto2));
    }

    @PatchMapping("/admin-dashboard/update/{email}")
    public String updateUserData(@RequestBody AdminDto adminDto, @PathVariable String email){
        adminService.updateAdmin(adminDto,email);
        return "successfully updated";
    }

    @GetMapping("/admin-dashboard/{email}")
    public Optional<Admin> getUserById(@PathVariable String email){
        return adminService.getAdminById(email);
    }
}
