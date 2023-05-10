package com.example.AnimeAPI.service;

import com.example.AnimeAPI.enums.UserType;
import com.example.AnimeAPI.model.User;
import com.example.AnimeAPI.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class UserServiceTest {


    private UserRepository userRepository;
    @MockBean
    private UserService userService;
    @Test
    void createUserReturnsNewlyCreatedUser() {
        User userObj = new User("Tobe","email@email.com","1123", UserType.GENERAL);
        User newlyCreatedUser = userService.createUser(userObj);
        assertNotNull(newlyCreatedUser);
        assertEquals("Tobe",newlyCreatedUser.getUserName());
        assertEquals("email@email.com",newlyCreatedUser.getEmail());
        assertEquals(UserType.GENERAL,newlyCreatedUser.getUserType());
    }
}