package com.sagarannaldas.online_store;

import com.sagarannaldas.online_store.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class OnlineStoreApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OnlineStoreApplication.class, args);
        var user = User.builder()
                .id(1L)
                .email("email")
                .password("password")
                .build();
    }

}
