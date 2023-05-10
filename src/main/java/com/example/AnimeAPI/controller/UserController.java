package com.example.AnimeAPI.controller;

import com.example.AnimeAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
@RequestMapping(path = "/api")
public class UserController {

    private final UserService userService;
    private HashMap<String , Object> response;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



}
