package com.social.media.feed.account.service.application.impl;

import com.social.media.feed.account.service.application.dto.AccountDetails;
import com.social.media.feed.account.service.application.port.service.AuthenticationService;
import com.social.media.feed.account.service.application.util.JwtTokenUtil;
import com.social.media.feed.account.service.domain.entity.Account;
import com.social.media.feed.account.service.domain.exception.AccountDomainException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public String authenticateAccount(Account account) {
        Authentication authentication;
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword());
            authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch(Exception e) {
            throw new AccountDomainException(e.getMessage());
        }
        AccountDetails accountDetails = (AccountDetails) authentication.getPrincipal();
        return jwtTokenUtil.generateToken(accountDetails.getUsername());
    }
}
