package com.example.AnimeAPI.service;

import com.example.AnimeAPI.exceptions.InformationExistException;
import com.example.AnimeAPI.model.User;
import com.example.AnimeAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User createUser(User userObject) {
        Optional<User> user = userRepository.findUserByUserName(userObject.getUserName());
        if(user.isEmpty()){
           return userRepository.save(userObject);
        }
        throw new InformationExistException("User with username " + userObject.getUserName() + " already exists.");
    }
}
