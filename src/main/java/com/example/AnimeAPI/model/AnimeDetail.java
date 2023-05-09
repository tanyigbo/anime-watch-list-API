package com.example.AnimeAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "anime_details")
public class AnimeDetail {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "anime_id")
    private Anime anime;


    public AnimeDetail() {
    }

    public AnimeDetail(Long id, Genre genre, Anime anime) {
        this.id = id;
        this.genre = genre;
        this.anime = anime;
    }

    public Long getId() {
        return id;
    }


    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Anime getAnime() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }

    @Override
    public String toString() {
        return "AnimeDetail{" +
                "id=" + id +
                ", genre=" + genre +
                ", anime=" + anime +
                '}';
    }
}
