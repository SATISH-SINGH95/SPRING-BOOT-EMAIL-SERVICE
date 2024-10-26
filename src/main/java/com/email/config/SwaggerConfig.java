package com.email.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info().title("EMAIL-SERVICE")
                .description("send email with or without attachment, to multiple peoples")
                .version("v0.0.1"));
    }
}
