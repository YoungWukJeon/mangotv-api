package com.study.mangotv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = {"com.study.mangotv"})
public class MangoTvApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(MangoTvApiApplication.class, args);
	}
}