package com.metamorfoz.track_employee.common.auth;


import java.io.IOException;

import com.metamorfoz.track_employee.common.exceptions.TokenException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

@Service
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(httpServletRequest);
        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (TokenException ex) {
            SecurityContextHolder.clearContext();
            httpServletResponse.sendError(HttpStatus.NO_CONTENT.value(), ex.getMessage());
            return;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
