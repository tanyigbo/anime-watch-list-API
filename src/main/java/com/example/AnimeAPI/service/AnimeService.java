package com.example.AnimeAPI.service;

import com.example.AnimeAPI.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimeService {
    private final AnimeRepository animeRepository;

    @Autowired
    public AnimeService(AnimeRepository animeRepository){this.animeRepository = animeRepository;}
}
