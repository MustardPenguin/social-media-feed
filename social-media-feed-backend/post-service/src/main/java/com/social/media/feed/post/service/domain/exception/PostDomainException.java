package com.social.media.feed.post.service.domain.exception;

import com.social.media.feed.domain.exception.DomainException;

public class PostDomainException extends DomainException {
    public PostDomainException(String message) {
        super(message);
    }

    public PostDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
