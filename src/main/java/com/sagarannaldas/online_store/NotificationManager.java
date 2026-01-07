package com.sagarannaldas.online_store;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NotificationManager {
    private NotificationsService notificationsService;

    public NotificationManager(@Qualifier("email") NotificationsService notificationsService) {
        this.notificationsService = notificationsService;
    }

    public void sendNotification(String phoneNumber, String message) {
        notificationsService.sendNotification(phoneNumber, message);
    }
}
