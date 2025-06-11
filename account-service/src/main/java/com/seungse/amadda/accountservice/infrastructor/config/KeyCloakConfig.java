package com.seungse.amadda.accountservice.infrastructor.config;

import lombok.RequiredArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@RequiredArgsConstructor
@Configuration
public class KeyCloakConfig {

    private final KeyCloakProperties keyCloakProperties;

    @Bean
    public Keycloak keyCloak() {
        return KeycloakBuilder.builder()
                .serverUrl(keyCloakProperties.getServerUrl())
                .realm(keyCloakProperties.getRealm())
                .clientId(keyCloakProperties.getClientId())
                .clientSecret(keyCloakProperties.getClientSecret())
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .username("admin")
                .password("password")
                .build();
    }
}
