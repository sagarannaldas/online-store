package com.sagarannaldas.online_store.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService implements NotificationService {

    @Value("${mail.host}")
    private String mailHost;

    @Value("${mail.port}")
    private String port;

    @Override
    public void send(String message, String recipientEmail) {

        System.out.println("Recipient Email: " + recipientEmail);
        System.out.println("Message: " + message);
        System.out.println("Host: " + mailHost);
        System.out.println("Port: " + port);
    }
}
