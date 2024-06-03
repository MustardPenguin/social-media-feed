package com.social.media.feed.api.gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRouteConfig {

    private final AuthenticationFilter authenticationFilter;

    public GatewayRouteConfig(AuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("register-account", route -> route
                        .path("/api/account")
                        .uri("lb://account-service"))
                .route("get-account", route -> route
                        .path("/api/account/{accountId}")
                        .and().method("GET")
                        .uri("lb://account-service"))
                .route("follow-service", route -> route
                        .path("/api/follow/**")
                        .filters(filter -> filter.filter(authenticationFilter))
                        .uri("lb://account-service"))
                .route("get-follows", route -> route
                        .path("/api/account/{accountId}/followers", "/api/account/{accountId}/following")
                        .uri("lb://account-service"))
                .route("authenticate-account", route -> route
                        .path("/api/authenticate")
                        .uri("lb://account-service"))
                .route("create-post", route -> route
                        .path("/api/post")
                        .filters(filter -> filter.filter(authenticationFilter))
                        .uri("lb://post-service"))
                .route("get-post", route -> route
                        .path("/api/post/**")
                        .and().method("GET")
                        .uri("lb://post-service"))
                .route("feed-service", route -> route
                        .path("/api/feed/**")
                        .uri("lb://feed-service"))
                .build();
    }
}
