package com.example.student.dto;

import lombok.Data;

@Data
public class SignInRequest {
    private String email;
    private String password;
    private String lastname;
}
