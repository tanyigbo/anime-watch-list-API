package com.example.AnimeAPI.controller;

import com.example.AnimeAPI.exception.InformationNotFoundException;
import com.example.AnimeAPI.model.AnimeDetail;
import com.example.AnimeAPI.service.AnimeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(path = "/api")
public class AnimeDetailController {

    static HashMap<String, Object> message = new HashMap<>();

    @Autowired
    private AnimeDetailService animeDetailService;

    @PostMapping(path = "/anime-details/{animeId}/{genreId}")
    public ResponseEntity<?> addAnimeToGenre(@PathVariable Long animeId, @PathVariable Long genreId){
            AnimeDetail animeDetail = animeDetailService.addAnimeToGenre(animeId, genreId);
            if (animeDetail != null) {
                message.put("message", "success");
                return new ResponseEntity<>(message, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }

    }
}
