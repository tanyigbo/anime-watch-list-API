package com.example.AnimeAPI.service;

import com.example.AnimeAPI.exception.InformationExistException;
import com.example.AnimeAPI.exception.InformationNotAcceptedException;
import com.example.AnimeAPI.exception.InformationNotFoundException;
import com.example.AnimeAPI.model.Anime;
import com.example.AnimeAPI.model.User;
import com.example.AnimeAPI.model.UserAnime;
import com.example.AnimeAPI.repository.UserAnimeRepository;
import com.example.AnimeAPI.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserAnimeService {

    private AnimeService animeService;

    private UserAnimeRepository userAnimeRepository;

    @Autowired
    public void setUserAnime(AnimeService animeService, UserAnimeRepository userAnimeRepository) {
        this.animeService = animeService;
        this.userAnimeRepository = userAnimeRepository;
    }

    public static int checkRating(int rating) {
        if (rating <= 0) return 0;
        else return Math.min(rating, 10);
    }

    public static String checkWatchStatus(String watchStatus) {
        String[] status = {"completed","watching","dropped","not-started"};
        if (watchStatus == null) {
            return "not-started";
        }

        for (int i = 0; i < status.length; i++) {
            if (watchStatus.toLowerCase().equals(status[i])) {
                return status[i];
            }
        }

        throw new InformationNotAcceptedException("Watch status (" + watchStatus + ") not accepted");
    }

    /**
     * Takes in a long anime id and anime request-body for user-anime watchlist.
     * Then, tries to find a record in the repository with current logged-in
     * user and existing anime as a set in the database. Throws exception if
     * already exist, otherwise, add a new record.
     *
     * @param animeId {Long}
     * @param userAnimeObj {Object}
     * @throws InformationExistException if record exists
     * @return UserAnime
     */
    public UserAnime addAnimeToUserWatchlist(Long animeId, UserAnime userAnimeObj) {
        Anime anime = animeService.getAnimeById(animeId);
        UserAnime userAnime = userAnimeRepository.findByUserAndAnime(AnimeService.getCurrentLoggedInUser(), anime);
        if (userAnime != null) {
            throw new InformationExistException("You have anime with id " + animeId + " in your watchlist");
        }
        userAnime = new UserAnime();
        userAnime.setUser(AnimeService.getCurrentLoggedInUser());
        userAnime.setAnime(anime);
        userAnime.setRating(checkRating(userAnimeObj.getRating()));
        userAnime.setWatchStatus(checkWatchStatus(userAnimeObj.getWatchStatus()));
        return userAnimeRepository.save(userAnime);
    }

    /**
     * Takes in a long anime id and user anime object and tries to find
     * an existing record connection between the logged-in user and given
     * anime id. If it exists, then update the rating and watch-status.
     *
     * @param animeId {Long}
     * @param userAnimeObj {Object}
     * @return ResponseEntity
     */
    public UserAnime updateAnimeInUserWatchlist(Long animeId, UserAnime userAnimeObj) {
        Anime anime = animeService.getAnimeById(animeId);
        UserAnime userAnime = userAnimeRepository.findByUserAndAnime(AnimeService.getCurrentLoggedInUser(), anime);
        if (userAnime != null) {
            userAnime = new UserAnime();
            userAnime.setRating(userAnimeObj.getRating());
            userAnime.setWatchStatus(userAnimeObj.getWatchStatus());
            return userAnimeRepository.save(userAnime);
        } else {
            throw new InformationNotFoundException("Anime with id " + animeId + " is not in your watchlist.");
        }
    }
}
