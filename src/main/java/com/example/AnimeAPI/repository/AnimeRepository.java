package com.example.AnimeAPI.repository;

import com.example.AnimeAPI.model.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {
   Optional<Anime> findByTitle(String title);

   Optional<Anime> findById(Long id);
}
