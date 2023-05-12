package com.example.AnimeAPI.controller;

import com.example.AnimeAPI.model.Genre;
import com.example.AnimeAPI.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /***
     * A POST endpoint that creates a genre
     * @param genre {Object}
     * @return ResponseEntity
     */
    @PostMapping(path = "/genres")
    public ResponseEntity<?> createGenre(@RequestBody Genre genre) {
        Genre newGenre = genreService.createGenre(genre);
        if (newGenre != null) {
            message.put("message", "success");
            message.put("data", newGenre);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else {
            message.put("message", "Insufficient rights");
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
        }
    }

    /**
     * A DELETE endpoint routing to deleteGenre() business logic.
     *
     * @param genreId {Long}
     * @return ResponseEntity
     */
    // http://localhost:8080/api/genres/1
    @DeleteMapping(path = "/genres/{genreId}")
    public ResponseEntity<?> deleteGenre(@PathVariable Long genreId) {
        Genre genre = genreService.deleteGenre(genreId);
        if (genre != null) {
            message.put("message", "Genre with id " + genreId + " deleted");
            message.put("data", genre);
            return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
        } else {
            message.put("message", "Insufficient rights");
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
        }
    }

    /**
     * A PUT endpoint routing to updateGenre business logic.
     *
     * @param genreId     {Long}
     * @param genreObject {Object}
     * @return ResponseEntity
     */
    // http://localhost:8080/api/genres/1
    @PutMapping(path = "/genres/{genreId}")
    public ResponseEntity<?> updateGenre(@PathVariable Long genreId, @RequestBody Genre genreObject) {
        Genre genre = genreService.updateGenre(genreId, genreObject);
        message.put("message", "success");
        message.put("data", genre);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * A GET method to find genre by ID.
     *
     * @param genreId {Long}
     * @return ResponseEntity
     */
    @GetMapping(path = "/genres/{genreId}")
    public ResponseEntity<?> getGenreById(@PathVariable Long genreId) {
        Genre genre = genreService.getGenreById(genreId);
        message.put("message", "success");
        message.put("data", genre);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
