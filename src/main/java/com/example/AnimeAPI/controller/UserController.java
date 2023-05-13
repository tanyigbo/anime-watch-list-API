package com.example.AnimeAPI.controller;

import com.example.AnimeAPI.exception.InformationExistException;
import com.example.AnimeAPI.model.User;
import com.example.AnimeAPI.model.login.LoginRequest;
import com.example.AnimeAPI.model.login.LoginResponse;
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
    // http://localhost:{portNumber}/auth/users
    public ResponseEntity<?> getAllUsers(){
        response = new HashMap<>();
        List<User> users = userService.getAllUsers();
        if (users != null) {
            response.put("message","success");
            response.put("data", users);
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        } else {
            response.put("message", "Insufficient rights");
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
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
            User newUser = userService.createUser(userObj);
            response.put("message","user created");
            response.put("data",newUser);
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }catch (InformationExistException e){
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.CONFLICT);
        }
    }

    /**
     * This method is responsible for logging in a user. It takes a loginRequest object as input, which contains
     * the user's login credentials. It then interacts with the userService to authenticate the user.
     * If the authentication is successful, it returns a LoginResponse object wrapped in a ResponseEntity with an HTTP status of 200 (OK).
     * If the authentication fails, it throws a RuntimeException, returns an error message wrapped in a ResponseEntity with an HTTP status of 418 (I_AM_A_TEAPOT).
     *
     * @param loginRequest The LoginRequest object that contains the user's login credentials. This object is part of the HTTP request body.
     *
     * @return ResponseEntity<?> If authentication is successful, it returns a LoginResponse object with a message and the data (loginResponse.getMessage()).
     *                           If authentication fails, it returns an error message as part of a ResponseEntity with an HTTP status of 418.
     *
     * @throws RuntimeException If there's an issue during the authentication process, a RuntimeException is thrown.
     *
     * HTTP POST endpoint: http://localhost:{portNumber}/auth/users/login
     */
    @PostMapping(path = "/users/login")
    // http://localhost:{portNumber}/auth/users/login
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        response = new HashMap<>();
        try {
            LoginResponse loginResponse = userService.loginUser(loginRequest);
            response.put("message", "user logged in");
            response.put("data",loginResponse.getMessage());
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (RuntimeException e){
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.I_AM_A_TEAPOT);
        }
    }


}
