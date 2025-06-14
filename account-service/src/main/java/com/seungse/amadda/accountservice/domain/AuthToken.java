package com.seungse.amadda.accountservice.domain;

import lombok.Builder;
import org.keycloak.representations.AccessTokenResponse;

@Builder
public record AuthToken(
        String accessToken,
        String refreshToken,
        String tokenType,
        long refreshExpiresIn,
        long expiresIn
) {

    public static AuthToken newAuthTokenFrom(AccessTokenResponse accessTokenResponse) {
        return new AuthToken(accessTokenResponse.getToken(),
                accessTokenResponse.getRefreshToken(),
                accessTokenResponse.getTokenType(),
                accessTokenResponse.getExpiresIn(),
                accessTokenResponse.getExpiresIn());
    }
}
