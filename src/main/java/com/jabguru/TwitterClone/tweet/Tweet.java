package com.jabguru.TwitterClone.tweet;

import com.jabguru.TwitterClone.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
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

    private LocalDateTime tweetedAt;

    @ElementCollection
    private List<String> likes;

    @ElementCollection
    private List<String> commentIds;

    private Integer reshareCount;
    private Integer retweetedBy;
    private Integer repliedTo;
}
