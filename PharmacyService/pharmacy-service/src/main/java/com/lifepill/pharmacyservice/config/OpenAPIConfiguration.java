package com.lifepill.pharmacyservice.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Inupa Vihan");
        myContact.setEmail("inupavihan@gmail.com");

        Info information = new Info()
                .title("Pharmacy Service API")
                .version("1.0")
                .description("This API exposes endpoints of functionalities related to the pharmacy side operations.")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}
