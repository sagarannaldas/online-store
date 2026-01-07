package com.sagarannaldas.online_store;

import com.sagarannaldas.online_store.user.User;
import com.sagarannaldas.online_store.user.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class OnlineStoreApplication {

    public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(OnlineStoreApplication.class, args);
//		var orderService = context.getBean(OrderService.class);
//		var notificationService = context.getBean(NotificationManager.class);
//        orderService.placeOrder();
//		notificationService.sendNotification("0394302984", "hello world ");

//		var userService = context.getBean(UserService.class);
//		userService.registerUser(new User(1L,"abc@gmail.com", "1234","abcuser"));
//		userService.registerUser(new User(1L,"abc@gmail.com", "1234","abcuser"));

    }

}
