package com.example.AnimeAPI.model;

import com.example.AnimeAPI.enums.UserType;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false)
    private Enum<UserType> userType;

    @Column
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<UserAnime> userAnimeSet;

    public User() {
    }

    public User(String userName, String email, String password, Enum<UserType> userType) {
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Enum<UserType> getUserType() {
        return userType;
    }

    public Set<UserAnime> getUserAnimeSet() {
        return userAnimeSet;
    }

    public void setUserAnimeSet(Set<UserAnime> userAnimeSet) {
        this.userAnimeSet = userAnimeSet;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userType=" + userType +
                ", userAnimeSet=" + userAnimeSet +
                '}';
    }
}
