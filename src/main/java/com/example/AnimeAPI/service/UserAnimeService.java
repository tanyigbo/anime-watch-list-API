package com.example.AnimeAPI.service;

import com.example.AnimeAPI.exception.InformationExistException;
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

    public static User getCurrentLoggedInUser() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    public UserAnime addAnimeToUserWatchlist(Long animeId, UserAnime userAnimeObj) {
        Anime anime = animeService.getAnimeById(animeId);
        UserAnime userAnime = userAnimeRepository.findByUserAndAnime(getCurrentLoggedInUser(), anime);
        if (userAnime != null) {
            throw new InformationExistException("You have anime with id " + animeId + " in your watchlist");
        } else {
            userAnime = new UserAnime();
            userAnime.setUser(getCurrentLoggedInUser());
            userAnime.setAnime(anime);
            userAnime.setRating(userAnimeObj.getRating());
            userAnime.setWatchStatus(userAnimeObj.getWatchStatus());
            return userAnimeRepository.save(userAnime);
        }
    }
}
