package com.study.mangotv.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@RequiredArgsConstructor
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
                        .antMatchers("/**").permitAll()
                .and().headers().frameOptions().disable();  // X-Frame-Options in Spring Security 중지
    }

    // TODO: 2019-12-04 SWAGGER 등 기타 예외 url 등록
    @Override
    public void configure(WebSecurity web) {
        super.configure(web);
//        web.ignoring().antMatchers("");
    }
}
