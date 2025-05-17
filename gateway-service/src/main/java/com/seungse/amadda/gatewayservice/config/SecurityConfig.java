package com.seungse.amadda.gatewayservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http, ReactiveJwtDecoder jwtDecoder) {
        http
            .csrf(c -> c.disable())
            .authorizeExchange(exchanges -> exchanges
                .pathMatchers("/api/account/auth/**").permitAll()
                // .pathMatchers("/api/*/actuator/**").permitAll()
                .anyExchange().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(j -> j.jwtDecoder(jwtDecoder))  // JWT 검증 필터 등록
            );
        return http.build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        return ReactiveJwtDecoders.fromIssuerLocation("http://localhost:8080/realms/amadda");
    }

}
