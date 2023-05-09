package com.example.AnimeAPI.repository;

import com.example.AnimeAPI.model.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime, Long> {
}
