package com.sagarannaldas.online_store;

import com.sagarannaldas.online_store.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class OnlineStoreApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OnlineStoreApplication.class, args);
        var service = context.getBean(UserService.class);
        service.showEntityStates();

        /*var repository = context.getBean(UserRepository.class);

        var user = User.builder()
                .name("John")
                .email("john@gmail.com")
                .password("password")
                .build();
        repository.save(user);*/
    }

}
