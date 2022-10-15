package com.ra.bioskop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthEntryPoint authEntryPoint;

    @Bean
    public SecurityFilterChain apiWebSecurity(HttpSecurity http) throws Exception {

        http.formLogin().disable();

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/", "/api/v1/user/login", "/api/v1/user/register")
                .permitAll()

                // Admin
                .antMatchers("/api/v1/films/addAll", "/api/v1/films/add", "/api/v1/films/delete",
                        "/api/v1/films/addSchedule", "/api/v1/films/update")
                .hasRole("ADMIN")

                // Customer
                .antMatchers("/api/v1/user/update", "/api/v1/user/delete", "/api/invoice/download")
                .hasRole("CUSTOMER")

                .anyRequest().authenticated()

                .and().httpBasic()

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)

                .and()
                .authenticationProvider(authenticationProvider())

        ;
        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }
}
