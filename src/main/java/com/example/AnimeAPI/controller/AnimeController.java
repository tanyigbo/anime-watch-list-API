package com.example.AnimeAPI.controller;

import com.example.AnimeAPI.model.Anime;
import com.example.AnimeAPI.service.AnimeService;
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
public class AnimeController {
static HashMap<String, Object> message = new HashMap<>();
    @Autowired
    private AnimeService animeService;

    @GetMapping(path = "/animes")
    public ResponseEntity<?>  getAllAnimes(){
        List<Anime> animeList = animeService.getAllAnimes();
        message.put("message", "success");
        message.put("data", animeList);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
