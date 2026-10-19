package com.jobspot.service;

import com.jobspot.config.JwtConfig;
import com.jobspot.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

public class Jwt {
    private final Claims claims;
    private final SecretKey secretKey;

    Jwt(Claims claims, SecretKey secretKey){
        this.secretKey = secretKey;
        this.claims = claims;
    }

    public boolean isExpired(){
        return claims.getExpiration().before(new Date());
    }

    public Role getRoleFromToken(String token){
        return Role.valueOf(claims.get("role",String.class));
    }

    public Long getUserIdFromToken(String token){

        return Long.valueOf(claims.getSubject());
    }

    public String toString(){
        return Jwts.builder().claims(claims).signWith(secretKey).compact();
    }





}
