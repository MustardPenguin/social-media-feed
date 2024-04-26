package com.social.media.feed.post.service.application.rest.controller;

import com.social.media.feed.post.service.application.mapper.PostServiceMapper;
import com.social.media.feed.post.service.application.rest.model.CreatePostRequestBody;
import com.social.media.feed.post.service.domain.entity.Post;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostServiceMapper postServiceMapper;

    public PostController(PostServiceMapper postServiceMapper) {
        this.postServiceMapper = postServiceMapper;
    }

    @PostMapping
    public void createPost(
            @RequestBody @Valid CreatePostRequestBody createPostRequestBody,
            @RequestHeader("accountId") UUID accountId) {
        Post post = postServiceMapper.createPostRequestBodyToPost(createPostRequestBody);
        post.setAccountId(accountId);

    }
}
