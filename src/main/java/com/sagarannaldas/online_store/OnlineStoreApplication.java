package com.sagarannaldas.online_store;

import com.sagarannaldas.online_store.entities.Address;
import com.sagarannaldas.online_store.entities.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineStoreApplication {

    public static void main(String[] args) {
//        ApplicationContext context = SpringApplication.run(OnlineStoreApplication.class, args);
        var user = User.builder()
                .id(1L)
                .email("email")
                .password("password")
                .build();

        var address = Address.builder()
                .id(1L)
                .street("street")
                .city("city")
                .zip("zip")
                .state("state")
                .build();

        user.addAddress(address);
        System.out.println(user);
    }

}
