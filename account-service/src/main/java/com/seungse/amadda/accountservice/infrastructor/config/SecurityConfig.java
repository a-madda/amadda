package com.seungse.amadda.accountservice.infrastructor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> c.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()  // 여기선 @PreAuthorize 로 제한
            );
        return http.build();
    }
}