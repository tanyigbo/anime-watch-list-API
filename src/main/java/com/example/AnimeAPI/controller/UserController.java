package com.example.AnimeAPI.controller;

import com.example.AnimeAPI.model.User;
import com.example.AnimeAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping(path = "/api/auth")
public class UserController {

    private final UserService userService;
    private HashMap<String , Object> response;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * A Get request for all users
     * ResponseEntity is made up of an HTTP Status and a response object
     * The response object is composed of a status string a List of all User objects
     * @return ResponseEntity
     */
    @GetMapping(path = "/users")
    // http://localhost:{portNumber}/api/auth/users
    public ResponseEntity<?> getAllUsers(){
        response = new HashMap<>();
        List<User> users = userService.getAllUsers();
        response.put("message","success");
        response.put("data", users);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }
}
