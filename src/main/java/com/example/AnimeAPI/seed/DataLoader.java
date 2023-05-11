package com.example.AnimeAPI.seed;

import com.example.AnimeAPI.exception.InformationNotFoundException;
import com.example.AnimeAPI.model.Anime;
import com.example.AnimeAPI.model.Genre;
import com.example.AnimeAPI.model.User;
import com.example.AnimeAPI.model.UserAnime;
import com.example.AnimeAPI.repository.AnimeRepository;
import com.example.AnimeAPI.repository.GenreRepository;
import com.example.AnimeAPI.repository.UserAnimeRepository;
import com.example.AnimeAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.example.AnimeAPI.enums.WatchStatus.*;
import static com.example.AnimeAPI.enums.WatchStatus.DROPPED;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    private final AnimeRepository animeRepository;

    private final GenreRepository genreRepository;

    private final UserAnimeRepository userAnimeRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, AnimeRepository animeRepository,
                      GenreRepository genreRepository, UserAnimeRepository userAnimeRepository) {
        this.userRepository = userRepository;
        this.animeRepository = animeRepository;
        this.genreRepository = genreRepository;
        this.userAnimeRepository = userAnimeRepository;
    }

    @Override
    public void run(String... args) throws Exception{
        loadUserData();
        loadAnimeData();
        loadGenreData();
        loadUserAnimeData();
    }

    private User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new InformationNotFoundException("Not Found!");
        }
    }

    private Anime getAnime(Long id) {
        Optional<Anime> anime = animeRepository.findById(id);
        if (anime.isPresent()) {
            return anime.get();
        } else {
            throw new InformationNotFoundException("Not Found!");
        }
    }

    private void loadUserData(){
        if(userRepository.count() == 0){
            User user1 = new User("Username1", "password1", "GENERAL");
            User user2 = new User("Username2", "password2", "GENERAL");
            User admin1 = new User("Username3", "password3", "ADMIN");
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(admin1);
        }
    }

    private  void loadAnimeData(){
        if(animeRepository.count() == 0){
            Anime anime1 = new Anime("DBZ", "Monkey fights ugly aliens");
            Anime anime2 = new Anime("DBZ2", "Monkey fights ugly aliens part 2");
            Anime anime3 = new Anime("DBZ3", "Monkey fights ugly aliens part 3");
            Anime anime4 = new Anime("DBZ4", "Monkey fights ugly aliens part 4");
            Anime anime5 = new Anime("DBZ5", "Monkey fights ugly aliens part 5");
            Anime anime6 = new Anime("DBZ6", "Monkey fights ugly aliens part 6");
            animeRepository.save(anime1);
            animeRepository.save(anime2);
            animeRepository.save(anime3);
            animeRepository.save(anime4);
            animeRepository.save(anime5);
            animeRepository.save(anime6);
        }
    }

    private  void  loadGenreData(){
        if(genreRepository.count() == 0){
            Genre genre1 = new Genre("Action","Fight stuff");
            Genre genre2 = new Genre("Adventure","Explore places");
            Genre genre3 = new Genre("Drama","Hatch plans");
            genreRepository.save(genre1);
            genreRepository.save(genre2);
            genreRepository.save(genre3);
        }
    }

    private  void loadUserAnimeData() {
        if (userAnimeRepository.count() == 0) {
            User user1 = getUser(1L);
            User user2 = getUser(2L);
            User user3 = getUser(3L);

            Anime anime1 = getAnime(1L);
            Anime anime2 = getAnime(2L);
            Anime anime3 = getAnime(3L);

            UserAnime userAnime1 = new UserAnime(user1, anime1, 10, COMPLETED);
            UserAnime userAnime2 = new UserAnime(user1, anime2, 1, COMPLETED);
            UserAnime userAnime3 = new UserAnime(user2, anime1, 7, WATCHING);
            UserAnime userAnime4 = new UserAnime(user3, anime3, 0, PLAN_TO_WATCH);
            UserAnime userAnime5 = new UserAnime(user1, anime3, 0, DROPPED);

            userAnimeRepository.save(userAnime1);
            userAnimeRepository.save(userAnime2);
            userAnimeRepository.save(userAnime3);
            userAnimeRepository.save(userAnime4);
            userAnimeRepository.save(userAnime5);
        }
    }
}
