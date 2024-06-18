package com.social.media.feed.feed.service.infrastructure.repository.post;

import com.social.media.feed.feed.service.application.dto.PostCreatedEventModel;
import com.social.media.feed.feed.service.application.port.repository.PostRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PostRepositoryImpl implements PostRepository {

    private final PostRepositoryMapper postRepositoryMapper;
    private final PostJpaRepository postJpaRepository;

    public PostRepositoryImpl(PostRepositoryMapper postRepositoryMapper,
                              PostJpaRepository postJpaRepository) {
        this.postRepositoryMapper = postRepositoryMapper;
        this.postJpaRepository = postJpaRepository;
    }

    @Override
    public PostCreatedEventModel savePost(PostCreatedEventModel postCreatedEventModel) {
        PostEntity postEntity = postRepositoryMapper.postCreatedEventModelToPostEntity(postCreatedEventModel);
        PostEntity savedPostEntity = postJpaRepository.save(postEntity);
        return postRepositoryMapper.postEntityToPostCreatedEventModel(savedPostEntity);
    }

    @Override
    public List<PostCreatedEventModel> getRelevantPostsByAccountIds(List<UUID> accountId) {
        return postJpaRepository.findTop10ByAccountIdInOrderByCreatedAt(accountId)
                .stream()
                .map(postRepositoryMapper::postEntityToPostCreatedEventModel)
                .toList();
    }
}
