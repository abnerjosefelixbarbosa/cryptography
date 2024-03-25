package com.backend.java.backendjava.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SegurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf((csrf) -> csrf.disable())
                .headers((header) -> {
                    header.frameOptions((frame) -> {
                        frame.sameOrigin();
                    });
                })
                .cors(Customizer.withDefaults()).authorizeHttpRequests((auth) -> {
                    auth.anyRequest().permitAll();
                })
                .sessionManagement((session) -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
