package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication

public class BookingRoomApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookingRoomApplication.class, args);


    }

}
