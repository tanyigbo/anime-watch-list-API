package com.example.AnimeAPI.repository;

import com.example.AnimeAPI.model.UserAnime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAnimeRepository extends JpaRepository<UserAnime, Long> {
}
