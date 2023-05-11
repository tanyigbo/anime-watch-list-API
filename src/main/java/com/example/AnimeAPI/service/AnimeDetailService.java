package com.example.AnimeAPI.service;

import com.example.AnimeAPI.exception.InformationExistException;
import com.example.AnimeAPI.model.Anime;
import com.example.AnimeAPI.model.AnimeDetail;
import com.example.AnimeAPI.model.Genre;
import com.example.AnimeAPI.repository.AnimeDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    /***
     *
     * @param animeId
     * @param genreId
     * @return
     */
    public AnimeDetail addAnimeToGenre(Long animeId, Long genreId) throws InformationExistException{

        Genre genre = genreService.getGenreById(genreId);
        Anime anime = animeService.getAnimeById(animeId);
        Optional<AnimeDetail> animeDetail = animeDetailRepository.findByAnimeAndGenre(anime, genre);
        if (animeDetail.isPresent()){
            return null;
        }
       return animeDetailRepository.save(new AnimeDetail(genre,anime));

    }
}
