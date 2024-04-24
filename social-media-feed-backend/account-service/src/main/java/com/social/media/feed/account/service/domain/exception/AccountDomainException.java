package com.social.media.feed.account.service.domain.exception;

public class AccountDomainException extends RuntimeException {

    public AccountDomainException(String message) {
        super(message);
    }

    public AccountDomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountDomainException(Throwable cause) {
        super(cause);
    }

    public AccountDomainException() {
        super();
    }
}
