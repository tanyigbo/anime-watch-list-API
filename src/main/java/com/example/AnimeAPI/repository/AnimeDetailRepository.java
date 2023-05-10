package com.example.AnimeAPI.repository;

import com.example.AnimeAPI.model.AnimeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimeDetailRepository extends JpaRepository<AnimeDetail, Long> {
}
