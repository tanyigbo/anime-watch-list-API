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

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false)
    private final Enum<UserType> userType;

    @Column
    @OneToMany(mappedBy = "user",orphanRemoval = true)
    private Set<UserAnime> userAnimeSet;

    public User() {
        this.userType = UserType.GENERAL;
    }

    public User(String userName, String email, String password, Enum<UserType> userType) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    // getter
    public Long getId() {
        return id;
    }

    // getter
    public String getUserName() {
        return userName;
    }

    // setter
    public void setUserName(String userName) {
        this.userName = userName;
    }

    // getter
    public String getEmail() {
        return email;
    }

    // setter
    public void setEmail(String email) {
        this.email = email;
    }

    // getter
    public String getPassword() {
        return password;
    }

    // setter
    public void setPassword(String password) {
        this.password = password;
    }

    // getter
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
                ", email='" + email + '\'' +
                ", userType=" + userType +
                ", userAnimeSet=" + userAnimeSet +
                '}';
    }
}
