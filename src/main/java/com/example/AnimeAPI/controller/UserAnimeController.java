package com.example.AnimeAPI.controller;

import com.example.AnimeAPI.model.UserAnime;
import com.example.AnimeAPI.service.UserAnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(path = "/api")
public class UserAnimeController {

    static HashMap<String, Object> message;

    private UserAnimeService userAnimService;

    @Autowired
    public void setUserAnimeService(UserAnimeService userAnimService) {
        this.userAnimService = userAnimService;
    }

    // http://localhost:8080/api/user-anime/1
    @PostMapping(path = "/user-anime/{animeId}")
    public ResponseEntity<?> addAnimeToUserWatchlist(@PathVariable Long animeId, @RequestBody UserAnime userAnimeObj) {
        message = new HashMap<>();
        userAnimService.addAnimeToUserWatchlist(animeId, userAnimeObj);
        message.put("message", "Successfully added anime with id " + animeId + " to your watchlist");
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}
