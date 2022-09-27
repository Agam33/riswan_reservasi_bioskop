package com.ra.bioskop.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    public static final String USER_TAG = "user-service";

    @Bean
    public OpenAPI bioskopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                .title("Reservasi Bioskop")
//                .version(appVersion)
                .description("Reservasi Bioskop chapter 4 - 8"));
    }

    /*
       contains operation related to user API
    */
//    @Bean
//    public GroupedOpenApi userApi() {
//        return GroupedOpenApi.builder()
//                .group("Test-group")
//                .displayName("User")
//                .packagesToScan("com.ra.bioskop.controller.UserController")
//                .build();
//    }

}
