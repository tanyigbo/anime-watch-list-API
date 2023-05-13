package com.example.AnimeAPI.service;

import com.example.AnimeAPI.exception.InformationExistException;
import com.example.AnimeAPI.exception.InformationNotAcceptedException;
import com.example.AnimeAPI.exception.InformationNotFoundException;
import com.example.AnimeAPI.model.Anime;
import com.example.AnimeAPI.model.UserAnime;
import com.example.AnimeAPI.repository.UserAnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * Returns an integer between the ratings of 0
     * and 10.
     * @param rating {int}
     * @return {int}
     */
    public static int checkRating(int rating) {
        if (rating <= 0) return 0;
        else return Math.min(rating, 10);
    }

    /**
     * Returns a string watch-status if argument is valid.
     * Otherwise, throw new InformationNotAccepted exception.
     *
     * @param watchStatus {String}
     * @return status {String}
     * @throws InformationNotFoundException if argument does not match any status
     */
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

        throw new InformationNotAcceptedException("Watch status (" + watchStatus + ") not accepted.");
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
     *
     * @link #checkRating(int) CheckRating
     * @link #checkWatchStatus(String) CheckWatchStatus
     */
    public UserAnime addAnimeToUserWatchlist(Long animeId, UserAnime userAnimeObj) {
        Anime anime = animeService.getAnimeById(animeId);
        UserAnime userAnime = userAnimeRepository.findByUserAndAnime(AnimeService.getCurrentLoggedInUser(), anime);
        if (userAnime != null) {
            throw new InformationExistException("You have anime with id " + animeId + " in your watchlist.");
        }
        userAnime = new UserAnime();
        userAnime.setUser(AnimeService.getCurrentLoggedInUser());
        userAnime.setAnime(anime);
        // checks the rating before set
        userAnime.setRating(checkRating(userAnimeObj.getRating()));
        // checks the watchStatus before set
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
     * @throws InformationNotFoundException if JPA did not find record in UserAnime
     *
     * @link #checkRating(int) CheckRating
     * @link #checkWatchStatus(String) CheckWatchStatus
     */
    public UserAnime updateAnimeInUserWatchlist(Long animeId, UserAnime userAnimeObj) {
        Anime anime = animeService.getAnimeById(animeId);
        UserAnime userAnime = userAnimeRepository.findByUserAndAnime(AnimeService.getCurrentLoggedInUser(), anime);
        if (userAnime != null) {
            // checks the rating before set
            userAnime.setRating(checkRating(userAnimeObj.getRating()));
            // checks the watchStatus before set
            userAnime.setWatchStatus(checkWatchStatus(userAnimeObj.getWatchStatus()));
            return userAnimeRepository.save(userAnime);
        } else {
            throw new InformationNotFoundException("Anime with id " + animeId + " is not in your watchlist.");
        }
    }

    /**
     * Takes in a long anime id and searches for an existing record
     * in the userAnime repository. If it exists, then removes the record,
     * otherwise, throws new InformationNotFound exception.
     *
     * @param animeId {Long}
     * @return userAnime {Object}
     * @throws InformationNotFoundException if record does not exist in userAnime repository
     */
    public UserAnime deleteAnimeFromUserWatchlist(Long animeId) {
        Anime anime = animeService.getAnimeById(animeId);
        UserAnime userAnime = userAnimeRepository.findByUserAndAnime(AnimeService.getCurrentLoggedInUser(), anime);
        if (userAnime != null) {
            userAnimeRepository.delete(userAnime);
            return userAnime;
        } else {
            throw new InformationNotFoundException("Anime with id " + animeId + " is not in your watchlist.");
        }
    }
}
