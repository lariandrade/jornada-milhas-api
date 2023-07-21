package com.challenge.jornadamilhasapi.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Jornada Milhas")
                        .description("API REST de uma plataforma de turismo.")
                        .contact(new Contact()
                                .name("Larissa Andrade")
                                .email("larissa.sandrade@hotmail.com"))
                );
    }
}
