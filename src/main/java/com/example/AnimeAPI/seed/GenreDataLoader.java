package com.example.AnimeAPI.seed;

import com.example.AnimeAPI.model.Genre;
import com.example.AnimeAPI.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GenreDataLoader implements CommandLineRunner {

    @Autowired
    GenreRepository genreRepository;

    @Override
    public void run(String... args) throws Exception {
        loadGenreData();
    }

    private  void  loadGenreData(){
        if(genreRepository.count() == 0){
            Genre genre1 = new Genre("Teen","For ages13+");
            Genre genre2 = new Genre("Adult","For ages18+");
            genreRepository.save(genre1);
            genreRepository.save(genre2);
        }
    }
}
