package com.example.AnimeAPI.service;

import com.example.AnimeAPI.exception.InformationExistException;
import com.example.AnimeAPI.exception.InformationNotFoundException;
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

    /**
     * Takes in an integer of a genre id and tries to
     * find a genre record in the genre repository. If
     * it exists, then the record will be deleted from the repository.
     * Otherwise, throw not found exception.
     *
     * @param genreId {Long}
     * @return Genre {Object}
     */
    public Genre deleteGenre(Long genreId) {
        Optional<Genre> genre = genreRepository.findById(genreId);
        if (genre.isPresent()) {
            genreRepository.delete(genre.get());
            return genre.get();
        } else {
            throw new InformationNotFoundException("Genre with given id " + genreId + " does not exist.");
        }
    }

    /**
     * Takes an integer genre id and genre object, then
     * tries to find genre record with given id. If it is found
     * and the given name within the given object is different from the
     * genre name in the repository, then update the genre's record in the
     * repository. Otherwise, throws new exception for non-existing genre record or
     * given object's name equals record's name.
     *
     * @param genreId {Long}
     * @param genreObject {Object}
     * @return Genre {Object}
     */
    public Genre updateGenre(Long genreId, Genre genreObject) {
        Optional<Genre> genre = genreRepository.findById(genreId);
        if (genre.isPresent()) {
            if (genreObject.getName().equals(genre.get().getName())) {
                throw new InformationExistException("This name " + genreObject.getName() + " is already in use.");
            } else {
                genre.get().setName(genreObject.getName());
                genre.get().setDescription(genreObject.getDescription());
                return genreRepository.save(genre.get());
            }
        } else {
            throw new InformationNotFoundException("Genre with given id " + genreId + " does not exist.");
        }
    }

    /**
     * Retrieves a genre from the repository by its ID.
     *
     * @param genreId the ID of the genre to be retrieved.
     * @return the requested Genre object.
     * @throws InformationNotFoundException if the genre with the specified ID is not found.
     */
    public Genre getGenreById(Long genreId){
        Optional<Genre> genre = genreRepository.findById(genreId);
        if (genre.isEmpty()){
            throw new InformationNotFoundException("Genre with id" + genreId + "not found");
        }else {
            return genre.get();
        }
    }
}
