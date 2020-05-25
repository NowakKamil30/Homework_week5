package com.academiaspring.demo.boostraps;

import com.academiaspring.demo.enums.Currency;
import com.academiaspring.demo.models.User;
import com.academiaspring.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserService userService;

    @Autowired
    public DataLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userService.createUser(User
                .builder()
                .currency(Currency.CZK)
                .email("kamilnowak@02.com")
                .firstName("Kamil")
                .lastName("Nowak")
                .build());
    }
}
