package com.example.AnimeAPI.service;

import com.example.AnimeAPI.model.Anime;
import com.example.AnimeAPI.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeService {
    private final AnimeRepository animeRepository;

    @Autowired
    public AnimeService(AnimeRepository animeRepository){this.animeRepository = animeRepository;}

    public List<Anime> getAllAnimes(){
        return animeRepository.findAll();
    }

    public Anime createAnime(Anime anime){
        return animeRepository.save(anime);
    }
}
