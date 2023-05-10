package com.example.AnimeAPI.service;

import com.example.AnimeAPI.model.User;
import com.example.AnimeAPI.repository.UserRepository;
import com.example.AnimeAPI.security.JWTUtils;
import com.example.AnimeAPI.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private AuthenticationManager authenticationManager;
//    private MyUserDetails myUserDetails;
//    private JWTUtils jwtUtils;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User userObject) {
        return new User();
    }
}
