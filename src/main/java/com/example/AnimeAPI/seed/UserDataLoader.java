package com.example.AnimeAPI.seed;

import com.example.AnimeAPI.enums.UserType;
import com.example.AnimeAPI.model.User;
import com.example.AnimeAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static com.example.AnimeAPI.enums.UserType.*;

@Component
public class UserDataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        loudUserData();
    }

    private void  loudUserData(){
        if(userRepository.count() == 0){
            User user1 = new User("Username1", "Email1@gmail.com","password1", GENERAL);
            User user2 = new User("Username2", "Email2@gmail.com","password2", GENERAL);
            User admin1 = new User("Username3", "Email3@gmail.com","password3", ADMIN);
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(admin1);
        }
    }
}
