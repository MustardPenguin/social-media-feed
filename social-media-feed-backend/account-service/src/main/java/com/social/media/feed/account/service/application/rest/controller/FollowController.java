package com.social.media.feed.account.service.application.rest.controller;

import com.social.media.feed.account.service.application.port.service.FollowService;
import com.social.media.feed.account.service.domain.entity.Follow;
import jakarta.ws.rs.HeaderParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/follow")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping("/{followeeId}")
    public ResponseEntity<String> follow(@PathVariable("followeeId") UUID followeeId, @RequestHeader("accountId") UUID accountId) {
        Follow follow = followService.followAccount(accountId, followeeId);
        return ResponseEntity.ok("Followed account with id " + follow.getFolloweeId() + " successfully!");
    }
}
