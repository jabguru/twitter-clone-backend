package com.jabguru.TwitterClone.notification.impl;

import com.jabguru.TwitterClone.notification.Notification;
import com.jabguru.TwitterClone.notification.NotificationRepository;
import com.jabguru.TwitterClone.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    @Override
    public void createNotification(Notification notification) {
        notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getNotifications(int userId) {
        return notificationRepository.findByUid(userId);
    }
}
