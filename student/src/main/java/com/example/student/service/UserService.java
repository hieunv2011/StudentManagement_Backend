package com.example.student.service;


import com.example.student.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();
    User getUserByEmail(String email);
}
