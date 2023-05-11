package com.example.AnimeAPI.service;

import com.example.AnimeAPI.model.Anime;
import com.example.AnimeAPI.model.Genre;
import com.example.AnimeAPI.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    /**
     * Retrieves all the genres from the repository.
     *
     * @return a list of all available genres.
     */
    public List<Genre> getAllGenres(){
        return genreRepository.findAll();
    }
}
