package com.example.AnimeAPI.controller;

import com.example.AnimeAPI.exceptions.InformationExistException;
import com.example.AnimeAPI.model.User;
import com.example.AnimeAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping(path = "/auth")
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

    /**
     * A Post request to create a new user
     * Creates a ResponseEntity that holds a response message and the created user
     * @param userObj The user login information
     * @return ResponseEntity with the responding message
     */
    @PostMapping(path = "/users/register")
    // http://localhost:{portNumber}/auth/users/register
    public ResponseEntity<?> createUser(@RequestBody User userObj){
        response = new HashMap<>();
        try {
            System.out.println(userObj.toString());
            User newUser = userService.createUser(userObj);
            System.out.println(newUser.toString());
            response.put("message","user created");
            response.put("data",newUser);
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }catch (InformationExistException e){
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.CONFLICT);
        }
    }
}
