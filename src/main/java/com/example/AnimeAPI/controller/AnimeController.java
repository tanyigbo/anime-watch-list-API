package com.example.AnimeAPI.controller;

import com.example.AnimeAPI.model.Anime;
import com.example.AnimeAPI.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/animes")
    public ResponseEntity<?> createAnime(@RequestBody Anime anime){
        Anime newAnime =  animeService.createAnime(anime);
        if(newAnime != null){
            message.put("message","success");
            message.put("data", newAnime);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }else {
            message.put("message", "Failed to create anime");
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/animes/{animeId}")
    public ResponseEntity<?> deleteAnime(@PathVariable Long animeId) {
        Anime anime = animeService.deleteAnime(animeId);
        message.put("message", "Anime with id " + animeId + " deleted");
        message.put("data", anime);
        return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
    }
}
