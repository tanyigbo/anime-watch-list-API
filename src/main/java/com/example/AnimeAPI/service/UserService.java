package com.example.AnimeAPI.service;

import com.example.AnimeAPI.exception.InformationExistException;
import com.example.AnimeAPI.exception.InformationNotFoundException;
import com.example.AnimeAPI.model.User;
import com.example.AnimeAPI.model.login.LoginRequest;
import com.example.AnimeAPI.repository.UserRepository;
import com.example.AnimeAPI.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private MyUserDetails myUserDetails;

    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder,
                       @Lazy AuthenticationManager authenticationManager, @Lazy MyUserDetails myUserDetails) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.myUserDetails = myUserDetails;
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User userObject) {
        Optional<User> user = userRepository.findUserByUsername(userObject.getUsername());
        if (user.isEmpty()) {
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        }
        throw new InformationExistException("User with username " + userObject.getUsername() + " already exists.");
    }

    public User findUserByUsername(String username){
        Optional<User> user = userRepository.findUserByUsername(username);
        if(user.isPresent()){
            return user.get();
        }
        throw new InformationNotFoundException("User with username "+ username + " was not found.");
    }

    public User loginUser(LoginRequest loginRequest){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            myUserDetails = (MyUserDetails) authentication.getPrincipal();
            return myUserDetails.getUser();
        }catch (Exception e)
        {
            throw new RuntimeException("Username or Password is incorrect.");
        }
    }

}
