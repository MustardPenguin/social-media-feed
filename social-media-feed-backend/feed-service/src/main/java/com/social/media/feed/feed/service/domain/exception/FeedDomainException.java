package com.social.media.feed.feed.service.domain.exception;

import com.social.media.feed.domain.exception.DomainException;

public class FeedDomainException extends DomainException {
    public FeedDomainException(String message) {
        super(message);
    }

    public FeedDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
