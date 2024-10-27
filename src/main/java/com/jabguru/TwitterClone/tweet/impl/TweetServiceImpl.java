package com.jabguru.TwitterClone.tweet.impl;

import com.jabguru.TwitterClone.tweet.Tweet;
import com.jabguru.TwitterClone.tweet.TweetRepository;
import com.jabguru.TwitterClone.tweet.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {
    private final TweetRepository tweetRepository;
    @Override
    public void shareTweet(Tweet tweet) {
        tweetRepository.save(tweet);
    }

    @Override
    public List<Tweet> getTweets() {
        return tweetRepository.findAll();
    }

    @Override
    public boolean updateTweet(Integer id, Tweet updatedTweet) {
        Optional<Tweet> tweetOptional = tweetRepository.findById(id);

        if (tweetOptional.isPresent()) {
            tweetRepository.save(updatedTweet);
            return true;
        }
        return false;
    }

    @Override
    public List<Tweet> getRepliesToTweet(Integer tweetId) {
        return tweetRepository.findByRepliedTo(tweetId);
    }

    @Override
    public Tweet getTweetById(Integer id) {
        return tweetRepository.findById(id).orElse(null);
    }

    @Override
    public List<Tweet> getUserTweets(Integer userId) {
        return tweetRepository.findByUserId(userId);
    }

    @Override
    public List<Tweet> getTweetsByHashtag(String hashtag) {
        return tweetRepository.findByHashtagsContainsIgnoreCase(hashtag);
    }
}
