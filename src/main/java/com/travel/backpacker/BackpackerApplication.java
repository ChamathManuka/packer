package com.travel.backpacker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching

public class BackpackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackpackerApplication.class, args);
    }

}
