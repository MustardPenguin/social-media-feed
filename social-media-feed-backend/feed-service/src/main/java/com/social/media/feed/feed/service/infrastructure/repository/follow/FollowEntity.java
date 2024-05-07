package com.social.media.feed.feed.service.infrastructure.repository.follow;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "follows")
public class FollowEntity {

    @Id
    private UUID followId;
    private UUID followerId;
    private UUID followeeId;
}