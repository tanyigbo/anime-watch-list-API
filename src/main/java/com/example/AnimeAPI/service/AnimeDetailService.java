package com.example.AnimeAPI.service;

import com.example.AnimeAPI.exception.InformationExistException;
import com.example.AnimeAPI.model.Anime;
import com.example.AnimeAPI.model.AnimeDetail;
import com.example.AnimeAPI.model.Genre;
import com.example.AnimeAPI.model.User;
import com.example.AnimeAPI.repository.AnimeDetailRepository;
import com.example.AnimeAPI.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnimeDetailService {

public final AnimeDetailRepository animeDetailRepository;
public final GenreService genreService;

public final AnimeService animeService;

    @Autowired
    public AnimeDetailService(AnimeDetailRepository animeDetailRepository, GenreService genreService, AnimeService animeService) {
        this.animeDetailRepository = animeDetailRepository;
        this.genreService = genreService;
        this.animeService = animeService;
    }

    /**
     * Gets authorized user details.
     *
     * @return User {Object}
     */
    public static User getCurrentLoggedInUser() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    /**
     * This method adds a specified anime to a specified genre.
     * It fetches the Genre object and the Anime object using the provided IDs.
     * Then it checks if an AnimeDetail object (representing the association between the anime and the genre) already exists.
     * If it does exist, the method returns null to indicate that the anime is already associated with the genre.
     * If it doesn't exist, the method creates a new AnimeDetail object, saves it in the repository, and returns it.
     *
     * @param animeId The ID of the anime to add to the genre.
     * @param genreId The ID of the genre to add the anime to.
     * @return The newly created AnimeDetail object if the operation was successful, or null if the anime is already associated with the genre.
     * @throws InformationExistException If the anime or genre does not exist.
     */
    public AnimeDetail addAnimeToGenre(Long animeId, Long genreId) throws InformationExistException{
        User user = getCurrentLoggedInUser();
        if(user.getUserType().equalsIgnoreCase("ADMIN")) {
            Genre genre = genreService.getGenreById(genreId);
            Anime anime = animeService.getAnimeById(animeId);
            Optional<AnimeDetail> animeDetail = animeDetailRepository.findByAnimeAndGenre(anime, genre);
            if (animeDetail.isPresent()) {
                return null;
            }
            return animeDetailRepository.save(new AnimeDetail(genre, anime));
        }
        return null;
    }

    /**
     * This method removes a specified anime from a specified genre.
     * It fetches the Genre object and the Anime object using the provided IDs.
     * Then it checks if an AnimeDetail object (representing the association between the anime and the genre) exists.
     * If it does exist, the method deletes the AnimeDetail object from the repository and returns it.
     * If it doesn't exist, the method returns null to indicate that the anime was not associated with the genre.
     *
     * @param animeId The ID of the anime to remove from the genre.
     * @param genreId The ID of the genre to remove the anime from.
     * @return The deleted AnimeDetail object if the operation was successful, or null if the anime was not associated with the genre.
     */
    public AnimeDetail removeAnimeFromGenre(Long animeId, Long genreId){
        User user = getCurrentLoggedInUser();
        if(user.getUserType().equalsIgnoreCase("ADMIN")) {
            Genre genre = genreService.getGenreById(genreId);
            Anime anime = animeService.getAnimeById(animeId);
            Optional<AnimeDetail> animeDetail = animeDetailRepository.findByAnimeAndGenre(anime, genre);
            if (animeDetail.isPresent()) {
                animeDetailRepository.delete(animeDetail.get());
                return animeDetail.get();
            }
            return null;
        }
        return null;
    }

}
