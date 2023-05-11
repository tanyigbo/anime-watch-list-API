package com.example.AnimeAPI.repository;

import com.example.AnimeAPI.model.Anime;
import com.example.AnimeAPI.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findByName(String name);
//    Optional<Genre> findById(Long id);
}
