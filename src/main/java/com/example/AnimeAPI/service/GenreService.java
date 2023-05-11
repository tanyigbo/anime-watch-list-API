package com.example.AnimeAPI.service;

import com.example.AnimeAPI.exception.InformationNotFoundException;
import com.example.AnimeAPI.model.Anime;
import com.example.AnimeAPI.model.Genre;
import com.example.AnimeAPI.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    /**
     * Creates a new genre and saves it to the repository.
     * Throws an exception if the genre already exists.
     *
     * @param genreObject an instance of Genre to be created.
     * @return the saved Genre object.
     * @throws InformationNotFoundException if the genre already exists.
     */
    public Genre createGenre(Genre genreObject){
        Optional<Genre> genre = genreRepository.findByName(genreObject.getName());
        if(genre.isPresent()) {
            throw new InformationNotFoundException("This genre already exists:" + genreObject.getName());
        }else {
            return genreRepository.save(genreObject);
        }
    }

}
