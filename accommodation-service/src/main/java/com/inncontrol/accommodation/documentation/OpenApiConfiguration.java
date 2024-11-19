package com.inncontrol.accommodation.documentation;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI configuration class for InnControl Accommodation Service API
 * @author Sharon Antuanet Ivet Barrial Marin - u202114900
 * @version 1.0
 */

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI inncontrolPlatformOpenApi() {
        var openApi = new OpenAPI();
        openApi.info(new Info()
                .title("InnControl Platform API")
                .description("API for managing accommodation service platform")
                .version("v1.0.0")
                .termsOfService("https://swagger.io/terms/")
                .contact(new Contact()
                        .name("InnControl Developer Service")
                        .email("u202114900@upc.edu.pe")
                        .url("https://github.com/SharonBarrial"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("InnControl Platform Wiki Documentation")
                        .url("https://inncontrol-platform.wiki.github.io/docs"));

        return openApi;
    }
}