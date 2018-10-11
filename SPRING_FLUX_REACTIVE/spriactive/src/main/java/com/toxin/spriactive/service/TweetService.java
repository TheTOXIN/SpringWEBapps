package com.toxin.spriactive.service;

import com.toxin.spriactive.entity.Tweet;
import com.toxin.spriactive.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class TweetService {

    private final TweetRepository tweetRepository;

    @Autowired
    public TweetService(
        TweetRepository tweetRepository
    ) {
        this.tweetRepository = tweetRepository;
    }

    public Flux<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }

    public Mono<Tweet> createTweets(Tweet tweet) {
        return tweetRepository.save(tweet);
    }

    public Mono<Tweet> getTweetById(String tweetId) {
        return tweetRepository.findById(tweetId);
    }

    public Mono<Tweet> updateTweet(Tweet tweet, String tweetId) {
        return tweetRepository.findById(tweetId)
            .flatMap(existingTweet -> {
                existingTweet.setText(tweet.getText());
                return tweetRepository.save(existingTweet);
            });
    }

    public Mono<Tweet> deleteTweet(String tweetId) {
        return tweetRepository.findById(tweetId);
    }

    public Flux<Tweet> streamAllTweets() {
        return tweetRepository.findAll();
    }

    public Mono<Tweet> streamTweet(String tweetId) {
        return tweetRepository.findById(tweetId);
    }

}
