package com.social.media.feed.post.service.application.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @PostMapping
    public void createPost(@RequestHeader("accountId") UUID accountId) {
        System.out.println("Creating post for account id " + accountId);
    }
}
