package com.study.mangotv.common.config;

import com.study.mangotv.common.config.jwt.UserRegistrationJwtAuthenticationFilter;
import com.study.mangotv.common.config.jwt.UserRegistrationJwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserRegistrationJwtProvider userRegistrationJwtProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()   // csrf 보안 안하기
                .cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // session 생성 안하기
                .and()
                    .authorizeRequests()
                        .antMatchers("/api/v1/user/*").permitAll()
                        .antMatchers("/api/v1/auth/**").permitAll()
                        .antMatchers("/api/v1/user").authenticated()
                .and().headers().frameOptions().disable()  // X-Frame-Options in Spring Security 중지
                .and()
                    .addFilterBefore(new UserRegistrationJwtAuthenticationFilter(userRegistrationJwtProvider),
                            UsernamePasswordAuthenticationFilter.class);
    }

    // TODO: 2019-12-04 SWAGGER 등 기타 예외 url 등록
    @Override
    public void configure(WebSecurity web) {
        super.configure(web);
//        web.ignoring().antMatchers("");
    }
}
