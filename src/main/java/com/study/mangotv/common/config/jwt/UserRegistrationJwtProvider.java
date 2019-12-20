package com.study.mangotv.common.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@RequiredArgsConstructor
@Component
public class UserRegistrationJwtProvider {
    @Value("${jwt.secret-key.user-registration}")
    private String secretKey;
    public static final long MAX_TOKEN_VALID_MILLISECONDS = 60 * 60 * 1000L;    // 토큰 최대 만료 시간 1 hour 60 x 60
    private Collection<GrantedAuthority> defaultAuthorities;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        defaultAuthorities = new ArrayList<> ();
        defaultAuthorities.add(new SimpleGrantedAuthority("ROLE_USER_REGISTRATION_AUTHENTICATED"));
    }

    public String createToken(String email, LocalDateTime createDate) {
        Claims claims = Jwts.claims().setSubject(email);
        Date now = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        Date expireDate = new Date(now.getTime() + MAX_TOKEN_VALID_MILLISECONDS);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("mangotv.com")
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        return new UsernamePasswordAuthenticationToken(this.getJwtSubject(token), token, defaultAuthorities);
    }

    private String getJwtSubject(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return null;
        }
        return authorization.split(" ")[1];
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            Date now = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            return !claims.getBody().getExpiration().before(now);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
