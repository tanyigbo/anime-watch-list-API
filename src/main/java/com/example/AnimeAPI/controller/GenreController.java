package com.example.AnimeAPI.controller;

import com.example.AnimeAPI.model.Genre;
import com.example.AnimeAPI.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class GenreController {
    static HashMap<String, Object> message = new HashMap<>();

    @Autowired
    private GenreService genreService;

    /***
     * A GET endpoint that returns all genres
     * @return ResponseEntity
     */
    @GetMapping(path = "/genres")
    public ResponseEntity<?> getAllGenres(){
        List<Genre> genreList = genreService.getAllGenres();
        message.put("message", "success");
        message.put("data", genreList);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
