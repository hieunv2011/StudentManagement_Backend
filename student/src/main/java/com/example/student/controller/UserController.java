package com.example.student.controller;


import com.example.student.dto.GetUserDetails;
import com.example.student.entities.User;
import com.example.student.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hi User");
    }

    @GetMapping("/{email}")
    public ResponseEntity<String> getUserEmail(@PathVariable String email) {
        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(email);
        return ResponseEntity.ok("Email của người dùng là: " + userDetails.getUsername());
    }

    @GetMapping("/me")
    public ResponseEntity<User> getUserDetails(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

}
