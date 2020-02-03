package com.study.mangotv.common.config;

import com.study.mangotv.common.model.LoginSessionData;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class LoginSessionAuthenticationFilter extends OncePerRequestFilter {
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        LoginSessionData loginSessionData = (LoginSessionData) request.getSession().getAttribute("loginSessionData");

        if (loginSessionData != null) {
            SecurityContextHolder.getContext()
                    .setAuthentication(
                            this.getAuthentication(
                                    request.getSession().getId(),
                                    loginSessionData.toString(),
                                    loginSessionData.getStatus()
                            )
                    );
        }

        chain.doFilter(request, response);
    }

    private Authentication getAuthentication(String principal, String credentials, String userStatus) {
        return new UsernamePasswordAuthenticationToken(principal, credentials, this.getDefaultAuthorities(userStatus));
    }

    private Collection<GrantedAuthority> getDefaultAuthorities(String userStatus) {
        Collection<GrantedAuthority> defaultAuthorities = new ArrayList<> ();
        defaultAuthorities.add(new SimpleGrantedAuthority("ROLE_" + userStatus));
        return defaultAuthorities;
    }
}
