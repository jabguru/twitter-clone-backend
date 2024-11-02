package com.jabguru.TwitterClone.tweet.impl;

import com.jabguru.TwitterClone.image.ImageService;
import com.jabguru.TwitterClone.tweet.Tweet;
import com.jabguru.TwitterClone.tweet.TweetRepository;
import com.jabguru.TwitterClone.tweet.TweetService;
import com.jabguru.TwitterClone.tweet.websocket.TweetAction;
import com.jabguru.TwitterClone.tweet.websocket.TweetMessage;
import com.jabguru.TwitterClone.tweet.websocket.TweetMessageHandler;
import com.jabguru.TwitterClone.user.User;
import com.jabguru.TwitterClone.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {
    private final TweetRepository tweetRepository;
    private final UserService userService;
    private final ImageService imageService;
    private final TweetMessageHandler tweetMessageHandler;

    @Override
    public Tweet shareTweet(Integer userId, Tweet tweet, Optional<List<MultipartFile>> files) {
        User user = userService.getUser(userId);
        if (user != null) {
            tweet.setUser(user);

            if (files.isPresent()) {
                List<String> fileUrls = new ArrayList<>();

                files.get().forEach(file -> {
                    String url;
                    try {
                        url = imageService.saveImage(file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    fileUrls.add(url);
                });

                tweet.setImageLinks(fileUrls);
            }

            Tweet savedTweet = tweetRepository.save(tweet);

            TweetMessage tweetMessage = TweetMessage.builder().tweetAction(TweetAction.CREATE).tweet(savedTweet).build();

            try {
                tweetMessageHandler.sendMessage(tweetMessage);
            } catch (IOException e) {
                System.out.println("Error sending tweet message:" + e.getMessage());
            }


            return savedTweet;
        }
        return null;

    }

    @Override
    public List<Tweet> getTweets() {
        return tweetRepository.findAll().reversed();
    }

    @Override
    public boolean updateTweet(Integer id, Tweet updatedTweet) {
        Optional<Tweet> tweetOptional = tweetRepository.findById(id);

        if (tweetOptional.isPresent()) {
            Tweet tweet = tweetOptional.get();

            if (updatedTweet.getLikes() != null) {
                tweet.setLikes(updatedTweet.getLikes());
            }

            if (updatedTweet.getCommentIds() != null) {
                tweet.setCommentIds(updatedTweet.getCommentIds());
            }

            if (updatedTweet.getReshareCount() != null) {
                tweet.setReshareCount(updatedTweet.getReshareCount());
            }

            Tweet savedTweet = tweetRepository.save(tweet);

            TweetMessage tweetMessage = TweetMessage.builder().tweetAction(TweetAction.UPDATE).tweet(savedTweet).build();

            try {
                tweetMessageHandler.sendMessage(tweetMessage);
            } catch (IOException e) {
                System.out.println("Error sending tweet message:" + e.getMessage());
            }

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
