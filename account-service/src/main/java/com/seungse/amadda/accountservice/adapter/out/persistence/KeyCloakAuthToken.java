package com.seungse.amadda.accountservice.adapter.out.persistence;

import com.seungse.amadda.accountservice.domain.AuthToken;
import lombok.*;
import org.keycloak.representations.AccessTokenResponse;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KeyCloakAuthToken {

    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private long expiresIn;
    private long refreshExpiresIn;

    public KeyCloakAuthToken(AccessTokenResponse accessTokenResponse) {
        this.accessToken = accessTokenResponse.getToken();
        this.refreshToken = accessTokenResponse.getRefreshToken();
        this.tokenType = accessTokenResponse.getTokenType();
        this.expiresIn = accessTokenResponse.getExpiresIn();
        this.refreshExpiresIn = accessTokenResponse.getRefreshExpiresIn();
    }

    public static KeyCloakAuthToken newAuthTokenFrom(AccessTokenResponse accessTokenResponse) {
        return new KeyCloakAuthToken(accessTokenResponse);
    }


    public AuthToken toDomain() {
        return AuthToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType(tokenType)
                .expiresIn(expiresIn)
                .refreshExpiresIn(refreshExpiresIn)
                .build();
    }
}
