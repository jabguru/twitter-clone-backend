package com.jabguru.TwitterClone.tweet;

import com.jabguru.TwitterClone.image.ImageService;
import com.jabguru.TwitterClone.tweet.websocket.TweetMessage;
import com.jabguru.TwitterClone.tweet.websocket.TweetMessageHandler;
import com.jabguru.TwitterClone.user.User;
import com.jabguru.TwitterClone.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/tweets")
public class TweetController {
    private final TweetService tweetService;

    @PostMapping("/share")
    public ResponseEntity<Tweet> shareTweet(@ModelAttribute Tweet tweet, @RequestParam Integer userId, @RequestParam("files") Optional<List<MultipartFile>> files){
        Tweet createdTweet = tweetService.shareTweet(userId, tweet, files);
        if(createdTweet != null)
            return new ResponseEntity<>(createdTweet, HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Tweet>> getTweets(){
        return ResponseEntity.ok(tweetService.getTweets());
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String>  updateTweet(@PathVariable Integer id,@RequestBody Tweet tweet){
        boolean isUpdated = tweetService.updateTweet(id,tweet);
        if(isUpdated)
            return ResponseEntity.ok("Tweet updated successfully");
        return new ResponseEntity<>("Tweet not updated!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/replies/{tweetId}")
    public ResponseEntity<List<Tweet>> getRepliesToTweet(@PathVariable Integer tweetId){
        return ResponseEntity.ok(tweetService.getRepliesToTweet(tweetId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tweet> getTweetById(@PathVariable Integer id){
        Tweet tweet = tweetService.getTweetById(id);
        if (tweet != null)
            return new ResponseEntity<>(tweet, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Tweet>> getUserTweets(@PathVariable Integer userId){
        return ResponseEntity.ok(tweetService.getUserTweets(userId));
    }

    @GetMapping("/hashtag")
    public ResponseEntity<List<Tweet>> getTweetsByHashtag(@RequestParam String hashtag){
        return ResponseEntity.ok(tweetService.getTweetsByHashtag(hashtag));
    }

}
