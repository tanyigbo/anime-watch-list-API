package com.example.AnimeAPI.model;

import com.example.AnimeAPI.compositeKeys.AnimeListKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimeListKeyTest {

    private final AnimeListKey key1 = new AnimeListKey(1L, 1L);
    private final AnimeListKey key2 = new AnimeListKey(1L, 1L);
    private final AnimeListKey key3 = new AnimeListKey(1L, 2L);
    private final AnimeListKey key4 = new AnimeListKey(2L, 1L);

    @Test
    public void testEqualsReturnsTrueForKeyWithSameValues() {
        assertTrue(key1.isEqualTo(key2));
    }

    @Test
    public void testEqualsReturnsFalseForKeyDifferentAnimeID() {
        assertFalse(key1.isEqualTo(key3));
    }

    @Test
    public void testEqualsReturnsFalseForKeyDifferentUserID() {
        assertFalse(key1.isEqualTo(key4));
    }

}