package com.example.socialnet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SocialNetApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialNetApplication.class, args);
    }

}
