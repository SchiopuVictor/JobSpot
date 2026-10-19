package com.jobspot.filter;

import com.jobspot.service.Jwt;
import com.jobspot.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        String token = authHeader.replace("Bearer ","");

        Jwt jwt = jwtService.parseToken(token);

        if(jwt==null || jwt.isExpired()){
            filterChain.doFilter(request,response);
            return;
        }

        UsernamePasswordAuthenticationToken authorization = new UsernamePasswordAuthenticationToken(
                jwt.getUserIdFromToken(token),
                null,
                List.of(new SimpleGrantedAuthority("ROLE_"+jwt.getRoleFromToken(token)))
        );

        authorization.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authorization);

        filterChain.doFilter(request,response);
    }
}
