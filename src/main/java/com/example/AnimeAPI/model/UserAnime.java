package com.example.AnimeAPI.model;

import com.example.AnimeAPI.enums.WatchStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "user_anime")
public class UserAnime {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "anime_id")
    private Anime anime;

    @Column(nullable = false)
    private int rating;

    @Column(nullable = false)
    private String watchStatus;

    public UserAnime() {
    }

    public UserAnime(User user, Anime anime, int rating, String watchStatus) {
        this.user = user;
        this.anime = anime;
        this.rating = rating;
        this.watchStatus = watchStatus;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Anime getAnime() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getWatchStatus() {
        return watchStatus;
    }

    public void setWatchStatus(String watchStatus) {
        this.watchStatus = watchStatus;
    }

    @Override
    public String toString() {
        return "UserAnimeList{" +
                "id=" + id +
                ", user=" + user +
                ", anime=" + anime +
                ", rating=" + rating +
                ", watchStatus=" + watchStatus +
                '}';
    }
}
