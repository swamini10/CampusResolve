package com.campusresolve.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // Disable CSRF because we are using JWT
                .csrf(csrf -> csrf.disable())

                // Enable CORS
                .cors(Customizer.withDefaults())

                .authorizeHttpRequests(auth -> auth

                        // Allow browser preflight requests
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // Public APIs
                        .requestMatchers(
                                "/api/users/register",
                                "/api/users/login"
                        ).permitAll()

                        // Temporary email testing API
                        .requestMatchers("/api/email/**").permitAll()

                        // Admin APIs
                        .requestMatchers("/api/admin/**")
                        .hasRole("ADMIN")

                        // Student complaint APIs
                        .requestMatchers(HttpMethod.POST, "/api/complaints")
                        .hasRole("STUDENT")

                        .requestMatchers(HttpMethod.GET, "/api/complaints/my")
                        .hasRole("STUDENT")

                        // Admin complaint APIs
                        .requestMatchers(HttpMethod.GET, "/api/complaints")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/api/complaints/**")
                        .hasRole("ADMIN")

                        // All other APIs require authentication
                        .anyRequest().authenticated()
                )

                // JWT applications should be stateless
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                );

        // Run JWT filter before Spring Security's authentication filter
        http.addFilterBefore(
                jwtFilter,
                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }
}