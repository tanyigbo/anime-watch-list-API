package com.example.AnimeAPI.model;

import javax.persistence.*;

@Entity
@Table(name = "animes")// Table name in database
public class Anime {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;


}
