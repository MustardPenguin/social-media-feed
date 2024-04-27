package com.social.media.feed.post.service.application.configuration;


import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.social.media.feed.post.service")
public class FeignConfig {
}
