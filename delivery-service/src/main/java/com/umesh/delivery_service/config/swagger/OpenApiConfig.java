package com.umesh.delivery_service.config.swagger;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

        @Bean
        public OpenAPI deliveryServiceOpenAPI() {

                return new OpenAPI()
                                .info(new Info()
                                                .title("Delivery Service API")
                                                .description("APIs for Delivery, Retry, Dead Letter Queue and Operations Dashboard")
                                                .version("v1.0")
                                                .contact(new Contact()
                                                                .name("Umesh")
                                                                .email("your-email@example.com"))
                                                .license(new License()
                                                                .name("Apache 2.0")))
                                .externalDocs(new ExternalDocumentation()
                                                .description("Distributed Notification Platform"));
        }
}