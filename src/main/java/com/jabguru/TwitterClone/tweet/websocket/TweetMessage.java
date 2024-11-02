package com.jabguru.TwitterClone.tweet.websocket;

import com.jabguru.TwitterClone.tweet.Tweet;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TweetMessage {
    final TweetAction tweetAction;
    final Tweet tweet;
}
