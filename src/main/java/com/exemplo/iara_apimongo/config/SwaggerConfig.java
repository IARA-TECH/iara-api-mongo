package com.exemplo.iara_apimongo.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("IARA API - MongoDB")
                        .description("API para gestão de turnos e horários da aplicação IARA")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("IARA")
                                .email("iaratech.oficial@gmail.com "))
                        .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentação do Projeto no GitHub")
                        .url("https://github.com/IARA-TECH/iara-api-mongo/"));
    }
}
