package com.social.media.feed.account.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.social.media.feed")
public class AccountApplicationService {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplicationService.class, args);
    }
}
