package com.social.media.feed.post.service.application.rest.controller;

import com.social.media.feed.application.rest.model.HttpResponse;
import com.social.media.feed.post.service.application.mapper.PostServiceMapper;
import com.social.media.feed.post.service.application.port.service.PostService;
import com.social.media.feed.post.service.application.rest.model.CreatePostRequestBody;
import com.social.media.feed.post.service.application.rest.model.PostResponse;
import com.social.media.feed.post.service.application.util.PostServiceUtil;
import com.social.media.feed.post.service.domain.entity.Post;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostServiceMapper postServiceMapper;
    private final PostService postService;

    public PostController(PostServiceMapper postServiceMapper,
                          PostService postService) {
        this.postServiceMapper = postServiceMapper;
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<HttpResponse> createPost(
            @RequestBody @Valid CreatePostRequestBody createPostRequestBody,
            @RequestHeader("accountId") UUID accountId) {
        Post post = postServiceMapper.createPostRequestBodyToPost(createPostRequestBody);
        post.setAccountId(accountId);
        Post response = postService.createPost(post);
        HttpResponse httpResponse = new HttpResponse("Post created successfully with id " + response.getId().getValue().toString()
                + " for account id " + response.getAccountId() + "!");
        return ResponseEntity.ok(httpResponse);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPost(@PathVariable UUID postId) {
        Post post = postService.getPostById(postId);
        PostResponse postResponse = postServiceMapper.postToPostResponse(post);
        return ResponseEntity.ok(postResponse);
    }
}
