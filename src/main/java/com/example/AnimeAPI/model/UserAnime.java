package com.example.AnimeAPI.model;

import com.example.AnimeAPI.compositeKeys.AnimeListKey;
import com.example.AnimeAPI.enums.WatchStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class UserAnime {

    @EmbeddedId
    AnimeListKey id;

    @JsonIgnore
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne
    @MapsId("animeId")
    @JoinColumn(name = "anime_id")
    private Anime anime;

    @Column
    private int rating;

    @Column
    private WatchStatus watchStatus;

    public UserAnime() {
    }

    public UserAnime(AnimeListKey id, User user, Anime anime, int rating, WatchStatus watchStatus) {
        this.id = id;
        this.user = user;
        this.anime = anime;
        this.rating = rating;
        this.watchStatus = watchStatus;
    }

    public AnimeListKey getId() {
        return id;
    }

    public void setId(AnimeListKey id) {
        this.id = id;
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

    public WatchStatus getWatchStatus() {
        return watchStatus;
    }

    public void setWatchStatus(WatchStatus watchStatus) {
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
