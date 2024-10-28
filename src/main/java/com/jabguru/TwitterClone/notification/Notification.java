package com.jabguru.TwitterClone.notification;

import com.jabguru.TwitterClone.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Notification {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer uid;
    private String text;
    private Integer postId;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
}
