package com.social.media.feed.feed.service.application.port.repository;

import com.social.media.feed.feed.service.application.dto.PostCreatedEventModel;

import java.util.List;
import java.util.UUID;

public interface PostRepository {

    PostCreatedEventModel savePost(PostCreatedEventModel postCreatedEventModel);

    List<PostCreatedEventModel> getRelevantPostsByAccountIds(List<UUID> accountId);
}
