package com.jabguru.TwitterClone.notification;

import java.util.List;

public interface NotificationService {
    void createNotification(Notification notification);
    List<Notification> getNotifications(int userId);
//    Stream<RealtimeMessage> getLatestNotification();
}
