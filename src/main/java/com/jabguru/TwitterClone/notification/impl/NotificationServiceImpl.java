package com.jabguru.TwitterClone.notification.impl;

import com.jabguru.TwitterClone.notification.Notification;
import com.jabguru.TwitterClone.notification.NotificationRepository;
import com.jabguru.TwitterClone.notification.NotificationService;
import com.jabguru.TwitterClone.notification.websocket.NotificationMessageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationMessageHandler notificationMessageHandler;

    @Override
    public void createNotification(Notification notification) {
        Notification savedNotification = notificationRepository.save(notification);
        try {
            notificationMessageHandler.sendMessage(savedNotification);
        } catch (IOException e) {
            System.out.println("Error sending tweet message:" + e.getMessage());
        }
    }

    @Override
    public List<Notification> getNotifications(int userId) {
        return notificationRepository.findByUserId(userId);
    }
}
