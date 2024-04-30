package com.social.media.feed.account.service.infrastructure.repository.follow;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class FollowEntity {

    @Id
    private UUID followId;
    private UUID followerId;
    private UUID followingId;
}
