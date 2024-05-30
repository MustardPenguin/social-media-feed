package com.social.media.feed.account.service.application.rest.controller;

import com.social.media.feed.account.service.application.dto.FollowWithUsername;
import com.social.media.feed.account.service.application.port.service.FollowService;
import com.social.media.feed.account.service.application.rest.model.response.FollowsResponse;
import com.social.media.feed.account.service.domain.entity.Follow;
import com.social.media.feed.account.service.domain.exception.AccountDomainException;
import com.social.media.feed.application.rest.model.HttpResponse;
import jakarta.ws.rs.HeaderParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping("/follow/{followee}")
    public ResponseEntity<FollowWithUsername> followWithUUID(@PathVariable("followee") Object followee, @RequestHeader("accountId") UUID accountId) {
        FollowWithUsername followWithUsername;
        try {
            UUID followeeId = UUID.fromString(followee.toString());
            followWithUsername = followService.followAccountWithUUID(accountId, followeeId);
        } catch (IllegalArgumentException e) {
            String username = followee.toString();
            followWithUsername = followService.followAccountWithUsername(accountId, username);
        } catch (Exception e) {
            throw new AccountDomainException(e.getMessage());
        }
        return ResponseEntity.ok(followWithUsername);
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
