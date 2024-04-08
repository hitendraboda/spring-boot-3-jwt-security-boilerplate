package com.boda.security.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
//    Access Swagger UI using {host}/swagger-ui/index.html

    @Bean
    public OpenAPI openAPI() {
        Contact contact = new Contact();
        contact.setEmail("<<email>>");
        contact.setName("<<name>>");
        contact.setUrl("<<url>>");

        Info info = new Info()
                .contact(contact)
                .title("<<title>>")
                .version("<<version>>")
                .description("<<version>>");

        return new OpenAPI()
                .info(info);
    }
}
