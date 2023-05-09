package com.example.AnimeAPI.compositeKeys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AnimeListKey implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "anime_id")
    private Long animeId;

    public AnimeListKey() {
    }

    public AnimeListKey(Long userId, Long animeId) {
        this.userId = userId;
        this.animeId = animeId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getAnimeId() {
        return animeId;
    }

    public boolean isEqualTo(AnimeListKey key){
        if(this.getUserId() != key.getUserId()){
            return false;
        }
        if(this.getAnimeId() != key.getAnimeId()){
            return false;
        }
        return true;
    }
}
