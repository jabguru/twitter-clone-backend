package com.jabguru.TwitterClone.tweet;

import java.util.List;

public interface TweetService {
    Tweet shareTweet(Tweet tweet);
   List<Tweet> getTweets();
   // Stream<RealtimeMessage> getLatestTweet();
    boolean updateTweet(Integer id, Tweet tweet);
    List<Tweet> getRepliesToTweet(Integer tweetId);
    Tweet getTweetById(Integer id);
    List<Tweet> getUserTweets(Integer userId);
    List<Tweet> getTweetsByHashtag(String hashtag);
}
