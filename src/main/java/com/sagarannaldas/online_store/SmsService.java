package com.sagarannaldas.online_store;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("sms")
@Primary
public class SmsService implements NotificationsService {
    @Override
    public void sendNotification(String phoneNumber, String message) {
        System.out.println("Sending sms to " + phoneNumber);
    }
}
