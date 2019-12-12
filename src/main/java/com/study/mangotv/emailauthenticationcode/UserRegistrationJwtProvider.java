package com.study.mangotv.emailauthenticationcode;

import com.study.mangotv.persistence.emailauthenticationcode.EmailAuthenticationCodeEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class UserRegistrationJwtProvider {
    @Value("${jwt.secret-key}")
    private String secretKey;
    public static final long MAX_TOKEN_VALID_SECONDS = 60 * 60L;    // 토큰 최대 만료 시간 1 hour

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String email, LocalDateTime createDate) {
        Claims claims = Jwts.claims().setSubject(email);
        Date now = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        Date expireDate = new Date(now.getTime() + MAX_TOKEN_VALID_SECONDS);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("mangotv.com")
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getId(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            Date now = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            return !claims.getBody().getExpiration().before(now);
        } catch (Exception e) {
            return false;
        }
    }
}
