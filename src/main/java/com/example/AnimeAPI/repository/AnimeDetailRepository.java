package com.example.AnimeAPI.repository;

import com.example.AnimeAPI.model.Anime;
import com.example.AnimeAPI.model.AnimeDetail;
import com.example.AnimeAPI.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimeDetailRepository extends JpaRepository<AnimeDetail, Long> {

    Optional<AnimeDetail> findByAnimeAndGenre(Anime anime, Genre genre);
}
