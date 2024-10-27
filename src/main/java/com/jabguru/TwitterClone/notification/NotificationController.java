package com.jabguru.TwitterClone.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("/create")
    public ResponseEntity<String> createNotification(@RequestBody Notification notification){
        notificationService.createNotification(notification);
        return new ResponseEntity<>("Notification created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getNotifications(@PathVariable int userId){
        return ResponseEntity.ok(notificationService.getNotifications(userId));
    }
}
