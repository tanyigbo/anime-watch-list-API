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

    /***
     * A GET endpoint that returns all animes
     * @return ResponseEntity
     */
    @GetMapping(path = "/animes")
    public ResponseEntity<?>  getAllAnimes(){
        List<Anime> animeList = animeService.getAllAnimes();
        message.put("message", "success");
        message.put("data", animeList);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /***
     * A POST endpoint that creates an anime
     * @param anime
     * @return ResponseEntity
     */
    @PostMapping(path = "/animes")
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

    /**
     * A DELETE endpoint routing to deleteAnime() business logic.
     *
     * @param animeId {Long}
     * @return ResponseEntity
     */
    // http://localhost:8080/api/animes/1
    @DeleteMapping(path = "/animes/{animeId}")
    public ResponseEntity<?> deleteAnime(@PathVariable Long animeId) {
        Anime anime = animeService.deleteAnime(animeId);
        message.put("message", "Anime with id " + animeId + " deleted");
        message.put("data", anime);
        return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
    }

    /**
     * A PUT endpoint routing to updateAnime business logic.
     *
     * @param animeId {Long}
     * @param animeObject {Object}
     * @return ResponseEntity
     */
    // http://localhost:8080/api/animes/1
    @PutMapping(path = "/animes/{animeId}")
    public ResponseEntity<?> updateAnime(@PathVariable Long animeId, @RequestBody Anime animeObject) {
        Anime anime = animeService.updateAnime(animeId, animeObject);
        message.put("message", "success");
        message.put("data", anime);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * A GET method to find animes by ID.
     * @param animeId {Long}
     * @return ResponseEntity
     */
    @GetMapping(path = "/animes/{animeId}")
    public  ResponseEntity<?> getAnimeById(@PathVariable Long animeId){
        Anime anime = animeService.getAnimeById(animeId);
        message.put("message", "success");
        message.put("data", anime);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
