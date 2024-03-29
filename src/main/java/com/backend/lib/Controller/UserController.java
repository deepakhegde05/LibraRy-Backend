package com.backend.lib.Controller;
import com.backend.lib.Dto.*;
import com.backend.lib.Entity.User;
import com.backend.lib.Repo.UserRepo;
import com.backend.lib.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/user-sign")
    public String signUp(@RequestBody User user) {
        userService.addUser(user);
        return "successfully added";
    }

    @PostMapping("/homeee")
    public String signUp() {
        return "yugjkj";
    }

    @PostMapping("/user-login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody UserDto3 userDto3 ) {
        return ResponseEntity.ok(userService.login(userDto3));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(userService.refreshToken(refreshTokenRequest));
    }

    @GetMapping("/user")
    public String user() {
        return "user here";
    }

    @GetMapping("/onlyUser")
    public String onlyUser() {
        return "Only user here";
    }

    @DeleteMapping("admin-home/deleteUser/{email}")
    public String deleteUser(@PathVariable String email){
        userService.deleteUser(email);
        return "Successfully deleted";
    }

    @GetMapping("admin-home/getAll")
    public List<User> deleteUser(){
        return userRepo.findAll();

    }

    //for the side drawer get user by email

    @GetMapping("/side-drawer/{email}")
    public Optional<User> getUserById(@PathVariable String email){
        return userService.findThisUser(email);
    }

    @PatchMapping("/edit-profile/{email}")
    public String updateUserData(@RequestBody UserDto userDto, @PathVariable String email){
        userService.updateUser(userDto,email);
        return "successfully updated";
    }

    @PatchMapping("/updatePassword/{email}")
    public String updatePassword(@RequestBody UserDto2 userDto, @PathVariable String email){
        userService.updateUser(userDto,email);
        return "successfully updated";
    }

    @PatchMapping("/updateAllFromHistory/{email}")
    public String updateAllFromHistory(@RequestBody User user, @PathVariable String email){
        userService.updateAllUserDetails(user,email);
        return "successfully updated";
    }

    //to deactivate user
    @PostMapping("user-history/deactivateUser/{email}")
    public String deactiveUser(@PathVariable String email){
        userService.deactivateUser(email);
        return "Successfully deactivated";
    }

    @PostMapping("user-history/activateUser/{email}")
    public String activateUser(@PathVariable String email){
        userService.activateUser(email);
        return "Successfully activated";
    }

}
