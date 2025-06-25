package com.seungse.amadda.accountservice.adapter.out.persistence;

import com.seungse.amadda.accountservice.application.port.out.SignInAccountOutPortV1;
import com.seungse.amadda.accountservice.domain.AuthToken;
import com.seungse.amadda.accountservice.infrastructor.annotations.OutPortAdapter;
import com.seungse.amadda.accountservice.infrastructor.config.KeyCloakProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.representations.AccessTokenResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@OutPortAdapter
@RequiredArgsConstructor
public class SignInAccountOutPortV1Adapter implements SignInAccountOutPortV1 {

    private final KeyCloakProperties keyCloakProperties;

    @Override
    public Optional<AuthToken> signInAccount(String email, String password) {
        Map<String, Object> clientCredentials = new HashMap<>();
        clientCredentials.put("secret", keyCloakProperties.getClientSecret());
        clientCredentials.put("grant_type", "password");

        Configuration configuration =
                new Configuration(keyCloakProperties.getServerUrl(), keyCloakProperties.getRealm(), keyCloakProperties.getClientId(), clientCredentials, null);
        AuthzClient authzClient = AuthzClient.create(configuration);

        AccessTokenResponse accessTokenResponse = authzClient.obtainAccessToken(email, password);

        KeyCloakAuthToken authToken = KeyCloakAuthToken.newAuthTokenFrom(accessTokenResponse);
        return Optional.of(authToken.toDomain());
    }
}
