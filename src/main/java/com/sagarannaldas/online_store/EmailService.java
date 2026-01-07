package com.sagarannaldas.online_store;

import org.springframework.stereotype.Service;

@Service("email")
public class EmailService implements NotificationsService {
    @Override
    public void sendNotification(String email, String message) {
        System.out.println("Sending email to " + email);

    }
}
