package com.ra.bioskop.security;

import com.ra.bioskop.security.filters.AuthorizationJwtFilter;
import com.ra.bioskop.util.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] NO_AUTH = {
            // -- swagger ui
            "/v2/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui/**",
    };

    private final UserDetailsService userDetailService;

    private final BCryptPasswordEncoder passwordEncoder;

    private final AuthEntryPoint authEntryPoint;

    private final AuthorizationJwtFilter jwtFilter;

    public SecurityConfig(AuthorizationJwtFilter jwtFilter,
                          AuthEntryPoint authEntryPoint,
                          BCryptPasswordEncoder passwordEncoder,
                          UserDetailsService userDetailService) {
        this.jwtFilter = jwtFilter;
        this.authEntryPoint = authEntryPoint;
        this.passwordEncoder = passwordEncoder;
        this.userDetailService = userDetailService;
    }

    @Bean
    public SecurityFilterChain apiWebSecurity(HttpSecurity http) throws Exception {

        http.formLogin().disable();

        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/", "/api/auth/signin", "/api/auth/signup")
                .permitAll()

                // Admin
                .antMatchers("/api/v1/films/addAll", "/api/v1/films/add", "/api/v1/films/delete",
                        "/api/v1/films/addSchedule", "/api/v1/films/update", Constants.NOTIFICATION_ENDPOINT+"/**")
                .hasRole("ADMIN")

                // Customer
                .antMatchers("/api/v1/user/update", "/api/v1/user/delete", "/api/invoice/download")
                .hasRole("CUSTOMER")

                .anyRequest().authenticated()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)

                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers(NO_AUTH);
    }
}