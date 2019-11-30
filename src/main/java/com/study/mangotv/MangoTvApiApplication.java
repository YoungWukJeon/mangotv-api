package com.study.mangotv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.study.mangotv"})
public class MangoTvApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(MangoTvApiApplication.class, args);
	}
}