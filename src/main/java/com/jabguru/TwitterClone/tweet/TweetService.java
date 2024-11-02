package com.jabguru.TwitterClone.tweet;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface TweetService {
    Tweet shareTweet(Integer userId, Tweet tweet, Optional<List<MultipartFile>> files);
   List<Tweet> getTweets();
   // Stream<RealtimeMessage> getLatestTweet();
    boolean updateTweet(Integer id, Tweet tweet);
    List<Tweet> getRepliesToTweet(Integer tweetId);
    Tweet getTweetById(Integer id);
    List<Tweet> getUserTweets(Integer userId);
    List<Tweet> getTweetsByHashtag(String hashtag);
}
