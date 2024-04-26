package com.social.media.feed.account.service.domain.exception;

import com.social.media.feed.domain.exception.DomainException;

public class AccountDomainException extends DomainException {

    public AccountDomainException(String message) {
        super(message);
    }

    public AccountDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
