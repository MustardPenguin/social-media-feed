package com.social.media.feed.account.service;

import com.social.media.feed.account.service.application.impl.UserDetailsServiceImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class AccountTestBeanConfigurations {

    @Bean
    public UserDetailsService userDetailsService() {
        return Mockito.mock(UserDetailsServiceImpl.class);
    }
}
