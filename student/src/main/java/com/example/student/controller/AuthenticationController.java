package com.example.student.controller;

import com.example.student.dto.JwtAuthenticationResponse;
import com.example.student.dto.RefreshTokenRequest;
import com.example.student.dto.SignInRequest;
import com.example.student.dto.SignUpRequest;
import com.example.student.entities.User;
import com.example.student.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest){
        return  ResponseEntity.ok(authenticationService.signup(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signInRequest){
        return  ResponseEntity.ok(authenticationService.signin(signInRequest));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<JwtAuthenticationResponse> refreshtoken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return  ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
    @GetMapping("/user/{email}")
    public ResponseEntity<String> getUserEmail(@PathVariable String email) {
        String userEmail = authenticationService.getUserEmail(email);
        return ResponseEntity.ok("Email của người dùng là: " + userEmail);
    }
}
