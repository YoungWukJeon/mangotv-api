package com.study.mangotv.common.config.jwt;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserRegistrationJwtAuthenticationFilter extends OncePerRequestFilter {
    private UserRegistrationJwtProvider userRegistrationJwtProvider;

    public UserRegistrationJwtAuthenticationFilter(UserRegistrationJwtProvider userRegistrationJwtProvider) {
        this.userRegistrationJwtProvider = userRegistrationJwtProvider;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = userRegistrationJwtProvider.resolveToken(request);

        if (token != null && userRegistrationJwtProvider.validateToken(token)) {
            SecurityContextHolder.getContext().setAuthentication(userRegistrationJwtProvider.getAuthentication(token));
        }

        chain.doFilter(request, response);
    }
}
