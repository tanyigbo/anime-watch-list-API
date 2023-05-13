package com.example.AnimeAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "anime")// Table name in database
public class Anime {

    @JsonIgnore
    @OneToMany(mappedBy = "anime", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    Set<UserAnime> userAnimeList;

    @Column
    @OneToMany(mappedBy = "anime", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    Set<AnimeDetail> animeDetailSet;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;

    public Anime() {
    }

    public Anime(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Anime{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Set<UserAnime> getUserAnimeList() {
        return userAnimeList;
    }

    public void setUserAnimeList(Set<UserAnime> userAnimeList) {
        this.userAnimeList = userAnimeList;
    }

    public Set<AnimeDetail> getAnimeDetailSet() {
        return animeDetailSet;
    }

    public void setAnimeDetailSet(Set<AnimeDetail> animeDetailSet) {
        this.animeDetailSet = animeDetailSet;
    }
}
