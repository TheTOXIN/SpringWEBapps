package com.toxin.spriactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class SpriactiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpriactiveApplication.class, args);
    }

}
