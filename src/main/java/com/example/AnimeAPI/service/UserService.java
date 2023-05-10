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
    private final PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private MyUserDetails myUserDetails;
    private JWTUtils jwtUtils;

    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, @Lazy AuthenticationManager authenticationManager, @Lazy MyUserDetails myUserDetails, JWTUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.myUserDetails = myUserDetails;
        this.jwtUtils = jwtUtils;
    }

    public User createUser(User userObject) {
        return new User();
    }
}
