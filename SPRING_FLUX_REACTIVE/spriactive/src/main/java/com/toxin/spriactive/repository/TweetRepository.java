package com.toxin.spriactive.repository;

import com.toxin.spriactive.entity.Tweet;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
public interface TweetRepository extends ReactiveMongoRepository<Tweet, String> {

    @Tailable
    Flux<Tweet> findAllByCreatedAtAfter(LocalDateTime time);
}
