package com.study.mangotv.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()   // csrf 보안 안하기
                .cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // session 생성 안하기
                .and()
                    .authorizeRequests()
//                        .antMatchers("/api/v1/category/**").hasRole("NORMAL")
                        .anyRequest().permitAll()
                .and().headers().frameOptions().disable()  // X-Frame-Options in Spring Security 중지
                .and()
                    .addFilterBefore(new LoginSessionAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}