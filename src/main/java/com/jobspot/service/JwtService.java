package com.jobspot.service;

import com.jobspot.config.JwtConfig;
import com.jobspot.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtConfig jwtConfig;

    public String generateAccessToken(User user){
        return generateToken(user, jwtConfig.getAccessTokenTime());
    }

    public String generateRefreshToken(User user){
        return generateToken(user,jwtConfig.getRefreshTokenTime());
    }

    private String generateToken(User user, long currentMilliseconds) {
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim(user.getEmail(), "email")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * currentMilliseconds))
                .signWith(jwtConfig.getSecretKey())
                .compact();
    }

    public boolean validateToken(String token){
        try {
            Claims claims = getClaims(token);
            return claims.getExpiration().after(new Date());

        }catch (JwtException e ){
            return false;
        }

    }

    public Long getUserIdFromToken(String token){
        return Long.valueOf(getClaims(token).getSubject());
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(jwtConfig.getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}

