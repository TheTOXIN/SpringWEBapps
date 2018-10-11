package com.toxin.spriactive.controller;

import com.toxin.spriactive.service.TweetService;
import com.toxin.spriactive.entity.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.Duration;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RestController
public class TweetController {

    private final TweetService tweetService;

    @Autowired
    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @Bean
    RouterFunction<ServerResponse> getAllTweets() {
        return route(
            GET("/tweets"),
            req -> ok().body(
                tweetService.getAllTweets(),
                Tweet.class
            )
        );
    }

    @Bean
    RouterFunction<ServerResponse> getTweetById() {
        return route(
            GET("/tweets/{id}"),
            req -> ok().body(
                tweetService.getTweetById(req.pathVariable("id")),
                Tweet.class
            )
        );
    }

    @Bean
    RouterFunction<ServerResponse> deleteTweet() {
        return route(
            DELETE("/tweets/{id}"),
            req -> ok().body(
                tweetService.deleteTweet(req.pathVariable("id")),
                Tweet.class
            )
        );
    }

    @PostMapping("/tweets")
    public Mono<Tweet> createTweets(
        @Valid @RequestBody Tweet tweet
    ) {
        return tweetService.createTweets(tweet);
    }

    @PutMapping("/tweets/{id}")
    public Mono<ResponseEntity<Tweet>> updateTweet(
        @PathVariable(value = "id") String tweetId,
        @Valid @RequestBody Tweet tweet
    ) {
        return tweetService.updateTweet(tweet, tweetId)
            .map(updatedTweet -> new ResponseEntity<>(updatedTweet, HttpStatus.OK))
            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/stream/tweets", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tweet> streamAllTweets() {
        return tweetService.streamAllTweets();
    }

    @GetMapping(value = "/stream/tweets/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<Tweet> streamTweet(
        @PathVariable(value = "id") String tweetId
    ) {
        return tweetService.streamTweet(tweetId);
    }

    @GetMapping(value = "/testSSE/{hi}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> testSSE(
        @PathVariable(value = "hi") String hi
    ) {
        return Flux.interval(Duration.ofSeconds(1))
            .map(sq -> ServerSentEvent.<String>builder()
                .id(String.valueOf(sq))
                .event("periodic-event")
                .data(hi)
                .build()
            );
    }

}