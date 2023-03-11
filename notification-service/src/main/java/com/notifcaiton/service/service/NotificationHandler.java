package com.notifcaiton.service.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificationHandler implements INotificationHandler{

    private final INotifier notifier;

    @Override
    public void notify(String driverId) {
        notifier.sendEmail(driverId);
    }
}
