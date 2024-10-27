package com.jabguru.TwitterClone.tweet;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Integer> {
    List<Tweet> findByRepliedTo(Integer tweetId);
    List<Tweet> findByUserId(Integer userId);
    List<Tweet> findByHashtagsContainsIgnoreCase(String hashtag);
}
