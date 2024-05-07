package com.social.media.feed.account.service.application.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class JwtTokenUtil {

    private final long JWT_EXPIRATION = 10000 * 60 * 60 * 10;
    @Value("${jwtSecret}")
    private String JWT_SECRET;

    public String generateToken(String username, HashMap<String, String> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWT_SECRET)))
                .compact();
    }

    public Claims extractAllClaims(String jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWT_SECRET)))
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }
}
