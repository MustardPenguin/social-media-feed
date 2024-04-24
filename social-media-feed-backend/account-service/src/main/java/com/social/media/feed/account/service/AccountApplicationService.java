package com.social.media.feed.account.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AccountApplicationService {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplicationService.class, args);
    }
}
