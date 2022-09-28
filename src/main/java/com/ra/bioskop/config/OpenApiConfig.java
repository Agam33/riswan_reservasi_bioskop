package com.ra.bioskop.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI bioskopOpenAPI(@Value("${app.description}") String desc,
                                  @Value("${app.version}") String appVersion,
                                  @Value("${app.name}") String title) {
        return new OpenAPI()
                .info(new Info()
                .title(title)
                .version(appVersion)
                .description(desc));
    }
}
