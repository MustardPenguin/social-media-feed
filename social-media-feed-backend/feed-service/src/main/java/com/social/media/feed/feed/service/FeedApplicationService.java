package com.social.media.feed.feed.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.social.media.feed")
public class FeedApplicationService {
    public static void main(String[] args) {
        SpringApplication.run(FeedApplicationService.class, args);
    }
}
