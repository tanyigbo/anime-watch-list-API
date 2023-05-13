package com.example.AnimeAPI.service;

import com.example.AnimeAPI.exception.InformationNotFoundException;
import com.example.AnimeAPI.model.Anime;
import com.example.AnimeAPI.model.User;
import com.example.AnimeAPI.repository.AnimeRepository;
import com.example.AnimeAPI.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimeService {
    private final AnimeRepository animeRepository;

    @Autowired
    public AnimeService(AnimeRepository animeRepository){
        this.animeRepository = animeRepository;
    }

    public static User getCurrentLoggedInUser() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    /**
     * Retrieves all the anime from the repository.
     *
     * @return a list of all available anime.
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
        User user = getCurrentLoggedInUser();
        if (user.getUserType().equalsIgnoreCase("admin")) {
            Optional<Anime> anime = animeRepository.findByTitle(animeObject.getTitle());
            if(anime.isPresent()){
                throw new InformationNotFoundException("This anime already exists:" + animeObject.getTitle());
            }else {
                return animeRepository.save(animeObject);
            }
        }
        return null;
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
        User user = getCurrentLoggedInUser();
        if (user.getUserType().equalsIgnoreCase("admin")) {
            Optional<Anime> anime = animeRepository.findById(animeId);
            if (anime.isPresent()) {
                animeRepository.delete(anime.get());
                return anime.get();
            } else {
                throw new InformationNotFoundException("Anime with given id " + animeId + " does not exist.");
            }
        }
        return null;
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
            throw new InformationNotFoundException("Anime with id " + animeId + " not found");
        }else {
            return anime.get();
        }
    }


}
