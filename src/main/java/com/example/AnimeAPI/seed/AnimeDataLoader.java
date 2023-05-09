package com.example.AnimeAPI.seed;

import com.example.AnimeAPI.model.Anime;
import com.example.AnimeAPI.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AnimeDataLoader implements CommandLineRunner {

    @Autowired
    AnimeRepository animeRepository;

    @Override
    public void run(String... args) throws Exception{
        loudUserData();
    }

    private  void loudUserData(){
        if(animeRepository.count() == 0){
            Anime anime1 = new Anime("BDZ", "Monkey fights ugly anliens");
            Anime anime2 = new Anime("BDZ2", "Monkey fights ugly anliens part 2");
            Anime anime3 = new Anime("BDZ3", "Monkey fights ugly anliens part 3");
            Anime anime4 = new Anime("BDZ4", "Monkey fights ugly anliens part 4");
            Anime anime5 = new Anime("BDZ5", "Monkey fights ugly anliens part 5");
            Anime anime6 = new Anime("BDZ6", "Monkey fights ugly anliens part 6");
            animeRepository.save(anime1);
            animeRepository.save(anime2);
            animeRepository.save(anime3);
            animeRepository.save(anime4);
            animeRepository.save(anime5);
            animeRepository.save(anime6);
        }
    }
}
