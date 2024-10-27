package com.jabguru.TwitterClone.tweet;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/tweets")
public class TweetController {
    private final TweetService tweetService;

    @PostMapping("/share")
    public ResponseEntity<String> shareTweet(@RequestBody Tweet tweet){
        tweetService.shareTweet(tweet);
        return new ResponseEntity<>("Tweet shared successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Tweet>> getTweets(){
        return ResponseEntity.ok(tweetService.getTweets());
    }

    // Stream<RealtimeMessage> getLatestTweet();

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
