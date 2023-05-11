package com.example.AnimeAPI.service;

import com.example.AnimeAPI.exception.InformationNotFoundException;
import com.example.AnimeAPI.model.Anime;
import com.example.AnimeAPI.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimeService {
    private final AnimeRepository animeRepository;

    @Autowired
    public AnimeService(AnimeRepository animeRepository){this.animeRepository = animeRepository;}

    public List<Anime> getAllAnimes(){
        return animeRepository.findAll();
    }

    public Anime createAnime(Anime animeObject){
        Optional<Anime> anime = animeRepository.findByTitle(animeObject.getTitle());
        if(anime.isPresent()){
            throw new InformationNotFoundException("This anime already exists:" + animeObject.getTitle());
        }else {
            return animeRepository.save(animeObject);
        }

    }

    public Anime deleteAnime(Long animeId) {
        Optional<Anime> anime = animeRepository.findById(animeId);
        if (anime.isPresent()) {
            animeRepository.delete(anime.get());
            return anime.get();
        } else {
            throw new InformationNotFoundException("Anime with given id " + animeId + " does not exist.");
        }
    }
}
