package com.study.mangotv.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(this.openAPIInfo());
    }

    private Info openAPIInfo() {
        return new Info()
                .title("MangoTv API Documentation")
                .description("MangoTv API 개발에 사용되는 문서입니다.");
    }
}
