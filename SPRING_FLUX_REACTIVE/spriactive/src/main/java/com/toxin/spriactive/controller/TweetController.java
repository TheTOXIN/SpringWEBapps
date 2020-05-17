package com.toxin.spriactive.controller;

import com.toxin.spriactive.service.TweetService;
import com.toxin.spriactive.entity.Tweet;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class TweetController {

    private final TweetService tweetService;

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

    @GetMapping(value = "/stream/tweets", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Tweet> streamAllTweets() {
        return tweetService.streamAllTweets().delayElements(Duration.ofSeconds(3));
    }

    @GetMapping(value = "/stream/tweets/{id}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Mono<Tweet> streamTweet(
        @PathVariable(value = "id") String tweetId
    ) {
        return tweetService.streamTweet(tweetId);
    }

    @GetMapping(value = "/testSSE/{hi}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> testSSE(
        @PathVariable(value = "hi") String hi
    ) {
        return Flux.interval(Duration.ofSeconds(1)).map(i -> hi + i);
    }
}
