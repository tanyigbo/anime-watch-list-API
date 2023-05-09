package com.example.AnimeAPI.model;

import com.example.AnimeAPI.enums.UserType;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userName;

    @Column(unique = true)
    private String email;

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column
    private final Enum<UserType> userType;

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
}
