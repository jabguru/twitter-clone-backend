package com.jabguru.TwitterClone.notification;

import com.jabguru.TwitterClone.user.User;
import com.jabguru.TwitterClone.user.UserService;
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
    private final UserService userService;


    @PostMapping("/create")
    public ResponseEntity<String> createNotification(@RequestBody Notification notification, @RequestParam int userId){
        User user = userService.getUser(userId);

        if(user != null){
            notification.setUser(user);
            notificationService.createNotification(notification);
            return new ResponseEntity<>("Notification created successfully", HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getNotifications(@PathVariable int userId){
        return ResponseEntity.ok(notificationService.getNotifications(userId));
    }
}
