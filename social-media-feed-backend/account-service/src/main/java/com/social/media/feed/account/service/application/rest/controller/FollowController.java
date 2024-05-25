package com.social.media.feed.account.service.application.rest.controller;

import com.social.media.feed.account.service.application.dto.FollowWithUsername;
import com.social.media.feed.account.service.application.port.service.FollowService;
import com.social.media.feed.account.service.application.rest.model.response.FollowsResponse;
import com.social.media.feed.account.service.domain.entity.Follow;
import com.social.media.feed.application.rest.model.HttpResponse;
import jakarta.ws.rs.HeaderParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping("/follow/{followeeId}")
    public ResponseEntity<HttpResponse> follow(@PathVariable("followeeId") UUID followeeId, @RequestHeader("accountId") UUID accountId) {
        Follow follow = followService.followAccount(accountId, followeeId);
        HttpResponse httpResponse = new HttpResponse("Followed account with id " + follow.getFolloweeId() + " successfully!");

        return ResponseEntity.ok(httpResponse);
    }

    @GetMapping("/account/{accountId}/followers")
    public ResponseEntity<FollowsResponse> getFollowers(@PathVariable("accountId") UUID accountId) {
        List<FollowWithUsername> follows = followService.getFollowersByAccountId(accountId);
        FollowsResponse response = new FollowsResponse("Successfully retrieved!", follows);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/account/{accountId}/following")
    public ResponseEntity<FollowsResponse> getFollowing(@PathVariable("accountId") UUID accountId) {
        List<FollowWithUsername> follows = followService.getFolloweesByAccountId(accountId);
        FollowsResponse response = new FollowsResponse("Successfully retrieved!", follows);
        return ResponseEntity.ok(response);
    }
}
