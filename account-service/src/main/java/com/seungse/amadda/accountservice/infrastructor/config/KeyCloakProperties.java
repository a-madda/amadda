package com.seungse.amadda.accountservice.infrastructor.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "keycloak")
public class KeyCloakProperties {

    private String serverUrl;
    private String realm;
    private String clientId;
    private String clientSecret;

}
