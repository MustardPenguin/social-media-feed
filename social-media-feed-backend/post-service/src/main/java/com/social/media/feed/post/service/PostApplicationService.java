package com.social.media.feed.post.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.social.media.feed")
public class PostApplicationService {
    public static void main(String[] args) {
        SpringApplication.run(PostApplicationService.class, args);
    }
}
