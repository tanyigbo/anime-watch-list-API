package com.example.AnimeAPI.controller;

import com.example.AnimeAPI.model.AnimeDetail;
import com.example.AnimeAPI.service.AnimeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(path = "/api")
public class AnimeDetailController {

    static HashMap<String, Object> message;

    @Autowired
    private AnimeDetailService animeDetailService;

    /**
     * This endpoint adds a specified anime to a specified genre.
     * It first fetches the AnimeDetail object by calling the addAnimeToGenre method of AnimeDetailService.
     * If the AnimeDetail object is not null (indicating that the operation was successful), it returns a ResponseEntity
     * with a success message and HTTP status code 200 (OK).
     * If the AnimeDetail object is null (indicating that the operation was not successful), it returns a ResponseEntity
     * with HTTP status code 404 (NOT FOUND).
     *
     * @param animeId The ID of the anime to add to the genre.
     * @param genreId The ID of the genre to add the anime to.
     * @return A ResponseEntity containing a message and HTTP status code.
     */
    // http://localhost:8080/api/anime-details/1/1
     @PostMapping(path = "/anime-details/{animeId}/{genreId}")
    public ResponseEntity<?> addAnimeToGenre(@PathVariable Long animeId, @PathVariable Long genreId){
            message = new HashMap<>();
            AnimeDetail animeDetail = animeDetailService.addAnimeToGenre(animeId, genreId);
            if (animeDetail != null) {
                message.put("message", "success");
                return new ResponseEntity<>(message, HttpStatus.OK);
            }else {
                message.put("message", "failed");
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }
    }

    /**
     * This endpoint removes a specified anime from a specified genre.
     * It first fetches the AnimeDetail object by calling the removeAnimeFromGenre method of AnimeDetailService.
     * If the AnimeDetail object is not null (indicating that the operation was successful), it returns a ResponseEntity
     * with a success message and HTTP status code 204 (NO CONTENT).
     * If the AnimeDetail object is null (indicating that the operation was not successful), it returns a ResponseEntity
     * with HTTP status code 404 (NOT FOUND).
     *
     * @param animeId The ID of the anime to remove from the genre.
     * @param genreId The ID of the genre to remove the anime from.
     * @return A ResponseEntity containing a message and HTTP status code.
     */
    // http://localhost:8080/api/anime-details/1/1
    @DeleteMapping(path = "/anime-details/{animeId}/{genreId}")
    public ResponseEntity<?> removeAnimeFromGenre(@PathVariable Long animeId, @PathVariable Long genreId){
        message = new HashMap<>();
        AnimeDetail animeDetail = animeDetailService.removeAnimeFromGenre(animeId, genreId);
        if (animeDetail != null) {
            message.put("message", "success");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

}
