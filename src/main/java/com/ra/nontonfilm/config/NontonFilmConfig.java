package com.ra.nontonfilm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class NontonFilmConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /*
        pre-populate database
     */
    @Bean
    public Jackson2RepositoryPopulatorFactoryBean populatorFactoryBean() {
        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
        factory.setResources(new Resource[] {
                new ClassPathResource("genre-data.json"),
                new ClassPathResource("studio-data.json"),
                new ClassPathResource("seat-data.json"),
                new ClassPathResource("available-seat-studio-mawar-data.json"),
                new ClassPathResource("available-seat-studio-melati-data.json"),
        });
        return factory;
    }
}
