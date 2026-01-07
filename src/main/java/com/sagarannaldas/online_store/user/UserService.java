package com.sagarannaldas.online_store.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private NotificationService notificationService;

    public UserService(UserRepository userRepository, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    public void registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("User already exists" + user.getEmail());
        }

        userRepository.save(user);
        notificationService.send("registered successfully", user.getEmail());
    }

}
