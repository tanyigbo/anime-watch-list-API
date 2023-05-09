package com.example.AnimeAPI.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column
    @OneToMany(mappedBy = "genre",orphanRemoval = true)
    private Set<AnimeDetail> animeDetailsSet;

    public Genre() {
    }

    public Genre(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<AnimeDetail> getAnimeDetailsSet() {
        return animeDetailsSet;
    }

    public void setAnimeDetailsSet(Set<AnimeDetail> animeDetailsSet) {
        this.animeDetailsSet = animeDetailsSet;
    }
}
