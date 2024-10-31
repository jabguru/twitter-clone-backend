package com.jabguru.TwitterClone.tweet;

import com.jabguru.TwitterClone.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Tweet {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private User user;

    private String text;

    @ElementCollection
    private List<String> hashtags;

    private String link;

    @ElementCollection
    private List<String> imageLinks;

    @Enumerated(EnumType.STRING)
    private TweetType tweetType;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime tweetedAt;

    @ElementCollection
    private List<Integer> likes;

    @ElementCollection
    private List<Integer> commentIds;

    private Integer reshareCount;
    private String retweetedBy;
    private Integer repliedTo;
}
