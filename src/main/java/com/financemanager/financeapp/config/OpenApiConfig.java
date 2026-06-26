package com.financemanager.financeapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI financeAppOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Finance Manager")
                        .description("Documentation Swagger minimale de Finance Manager")
                        .version("1.0"));
    }
}



