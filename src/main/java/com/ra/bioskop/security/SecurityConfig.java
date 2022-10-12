package com.ra.bioskop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain apiWebSecurity(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/", "/api/v1/user/register", "/api/v1/films")
                .permitAll()

                // Admin
                .antMatchers("/api/v1/films/addAll", "/api/v1/films/add")
                .hasAnyRole("ADMIN")

                // Todo: Customer

                .anyRequest().authenticated()

                .and()
                .exceptionHandling()
                .authenticationEntryPoint((req, res, e) -> res.sendError(HttpStatus.UNAUTHORIZED.value()))

        ;
        return http.build();
    }
}
