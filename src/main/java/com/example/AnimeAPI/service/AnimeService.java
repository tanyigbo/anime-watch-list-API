package com.example.AnimeAPI.service;

import com.example.AnimeAPI.exception.InformationExistException;
import com.example.AnimeAPI.exception.InformationNotFoundException;
import com.example.AnimeAPI.model.Anime;
import com.example.AnimeAPI.model.Genre;
import com.example.AnimeAPI.repository.AnimeRepository;
import com.example.AnimeAPI.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimeService {
    private final AnimeRepository animeRepository;

    private final GenreRepository genreRepository;

    @Autowired
    public AnimeService(AnimeRepository animeRepository, GenreRepository genreRepository){
        this.animeRepository = animeRepository;
        this.genreRepository = genreRepository;
    }

    /**
     * Retrieves all the animes from the repository.
     *
     * @return a list of all available animes.
     */
    public List<Anime> getAllAnimes(){
        return animeRepository.findAll();
    }

    /**
     * Creates a new anime and saves it to the repository.
     * Throws an exception if the anime already exists.
     *
     * @param animeObject an instance of Anime to be created.
     * @return the saved Anime object.
     * @throws InformationNotFoundException if the anime already exists.
     */
    public Anime createAnime(Anime animeObject){
        Optional<Anime> anime = animeRepository.findByTitle(animeObject.getTitle());
        if(anime.isPresent()){
            throw new InformationNotFoundException("This anime already exists:" + animeObject.getTitle());
        }else {
            return animeRepository.save(animeObject);
        }

    }

    /**
     * Takes in an integer of an anime id and tries to
     * find an anime record in the anime repository. If
     * it exists, then the record will be deleted from the repository.
     * Otherwise, throw not found exception.
     *
     * @param animeId {Long}
     * @return Anime {Object}
     */
    public Anime deleteAnime(Long animeId) {
        Optional<Anime> anime = animeRepository.findById(animeId);
        if (anime.isPresent()) {
            animeRepository.delete(anime.get());
            return anime.get();
        } else {
            throw new InformationNotFoundException("Anime with given id " + animeId + " does not exist.");
        }
    }

    /**
     * Takes an integer anime id and anime object, then
     * tries to find anime record with given id. If it is found
     * and the given title within the given object is different from the
     * anime title in the repository, then update the anime's record in the
     * repository. Otherwise, throws new exception for non-existing anime record or
     * given object's title equals record's title.
     *
     * @param animeId {Long}
     * @param animeObject {Object}
     * @return Anime {Object}
     */
    public Anime updateAnime(Long animeId, Anime animeObject) {
        Optional<Anime> anime = animeRepository.findById(animeId);
        if (anime.isPresent()) {
            if (animeObject.getTitle().equals(anime.get().getTitle())) {
                throw new InformationExistException("This title " + animeObject.getTitle() + " is already in use.");
            } else {
                anime.get().setTitle(animeObject.getTitle());
                anime.get().setDescription(animeObject.getDescription());
                return animeRepository.save(anime.get());
            }
        } else {
            throw new InformationNotFoundException("Anime with given id " + animeId + " does not exist.");
        }
    }

    /**
     * Retrieves an anime from the repository by its ID.
     *
     * @param animeId the ID of the anime to be retrieved.
     * @return the requested Anime object.
     * @throws InformationNotFoundException if the anime with the specified ID is not found.
     */
    public Anime getAnimeById(Long animeId){
        Optional<Anime> anime = animeRepository.findById(animeId);
        if (anime.isEmpty()){
            throw new InformationNotFoundException("Anime with id" + animeId + "not found");
        }else {
            return anime.get();
        }

    }

    public Genre addAnimeToGenre(Long animeId, Genre genreObject){
        Optional<Anime> anime = animeRepository.findById(animeId);
        if(anime.isEmpty()){
            throw new InformationNotFoundException("Anime with id:" + animeId + "does not exist");
        }
        Optional<Genre> genre = genreRepository.findByName(String.valueOf(genreObject));
        if(genre.isEmpty()){
            throw new InformationNotFoundException("Genre:" + genreObject + "does not exist");
        }
        genreObject.setName(String.valueOf(anime));
        return genreRepository.save(genreObject);
    }
}
