package com.campusresolve.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // Public APIs
                        .requestMatchers(
                                "/api/users/register",
                                "/api/users/login"
                        ).permitAll()

                        // Admin Dashboard
                        .requestMatchers("/api/admin/**")
                        .hasRole("ADMIN")

                        // Student APIs
                        .requestMatchers(HttpMethod.POST, "/api/complaints")
                        .hasRole("STUDENT")

                        .requestMatchers(HttpMethod.GET, "/api/complaints/my")
                        .hasRole("STUDENT")

                        // Admin Complaint APIs
                        .requestMatchers(HttpMethod.GET, "/api/complaints")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/api/complaints/**")
                        .hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtFilter,
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}