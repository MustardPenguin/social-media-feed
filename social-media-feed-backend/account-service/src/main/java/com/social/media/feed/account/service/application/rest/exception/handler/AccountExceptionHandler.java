package com.social.media.feed.account.service.application.rest.exception.handler;

import com.social.media.feed.account.service.domain.exception.AccountDomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AccountExceptionHandler {

    @ExceptionHandler(AccountDomainException.class)
    public ResponseEntity<String> handleAccountDomainException(AccountDomainException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(500));
    }
}
