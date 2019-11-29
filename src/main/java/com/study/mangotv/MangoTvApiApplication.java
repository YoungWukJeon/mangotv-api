package com.study.mangotv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.study.mangotv.domain"})
@EntityScan(basePackages = {"com.study.mangotv.domain"})
@SpringBootApplication(scanBasePackages = {"com.study.mangotv.controller", "com.study.mangotv.domain"})
public class MangoTvApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangoTvApiApplication.class, args);
	}

}
