package com.example.AnimeAPI.model;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimeListKeyTest {

    private AnimeListKey key1 = new AnimeListKey(1L,1L);
    private AnimeListKey key2 = new AnimeListKey(1L,1L);
    private AnimeListKey key3 = new AnimeListKey(1L,2L);

    @Test
    public void testEqualsReturnsTrueForKeyWithSameValues() {
        assertTrue(key1.isEqualTo(key2));
    }

    @Test
    public void testEqualsReturnsFalseForKeyWithDifferentValues(){
        assertFalse(key1.isEqualTo(key3));
    }

}