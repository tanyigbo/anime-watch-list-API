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
    static HashMap<String, Object> message;

    @Autowired
    private AnimeService animeService;

    /***
     * A GET endpoint that returns all anime.
     * @return ResponseEntity
     */
    // http://localhost:8080/api/anime
    @GetMapping(path = "/anime")
    public ResponseEntity<?> getAllAnimes() {
        message = new HashMap<>();
        List<Anime> animeList = animeService.getAllAnimes();
        message.put("message", "success");
        message.put("data", animeList);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /***
     * A POST endpoint that creates an anime
     * @param anime {Object}
     * @return ResponseEntity
     */
    // http://localhost:8080/api/anime/add
    @PostMapping(path = "/anime/add")
    public ResponseEntity<?> createAnime(@RequestBody Anime anime) {
        message = new HashMap<>();
        Anime newAnime = animeService.createAnime(anime);
        if (newAnime != null) {
            message.put("message", "created anime");
            message.put("data", newAnime);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else {
            message.put("message", "Insufficient rights");
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
        }
    }

    /**
     * A DELETE endpoint routing to deleteAnime() business logic.
     *
     * @param animeId {Long}
     * @return ResponseEntity
     */
    // http://localhost:8080/api/animes/1
    @DeleteMapping(path = "/anime/{animeId}")
    public ResponseEntity<?> deleteAnime(@PathVariable Long animeId) {
        message = new HashMap<>();
        Anime anime = animeService.deleteAnime(animeId);
        if (anime != null) {
            message.put("message", "Anime with id " + animeId + " deleted");
            message.put("data", anime);
            return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
        } else {
            message.put("message", "Insufficient rights");
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
        }
    }

    /**
     * A GET method to find anime by ID.
     *
     * @param animeId {Long}
     * @return ResponseEntity
     */
    // http://localhost:8080/api/anime/1
    @GetMapping(path = "/anime/{animeId}")
    public ResponseEntity<?> getAnimeById(@PathVariable Long animeId) {
        message = new HashMap<>();
        Anime anime = animeService.getAnimeById(animeId);
        message.put("message", "success");
        message.put("data", anime);
        return new ResponseEntity<>(message, HttpStatus.FOUND);
    }


}
