package com.social.media.feed.api.gateway.configuration;

import com.social.media.feed.api.gateway.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Configuration
public class AuthenticationFilter implements GatewayFilter {

    private final JwtTokenUtil jwtTokenUtil;

    public AuthenticationFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if(!request.getHeaders().containsKey("Authorization")) {
            return getErrorResponse(exchange, HttpStatus.UNAUTHORIZED);
        }

        String token = request.getHeaders().getOrEmpty("Authorization").get(0).substring(7);

        if(!jwtTokenUtil.isTokenValid(token)) {
            return getErrorResponse(exchange, HttpStatus.FORBIDDEN);
        }

        Claims claims = jwtTokenUtil.extractAllClaims(token);
        String username = claims.getSubject();
        String accountId = claims.get("accountId", String.class);

        ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
                .header("accountId", accountId)
                .build();

        return chain.filter(exchange);
    }

    private Mono<Void> getErrorResponse(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }
}
