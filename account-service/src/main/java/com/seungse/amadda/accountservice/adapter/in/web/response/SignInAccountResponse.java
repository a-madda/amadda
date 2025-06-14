package com.seungse.amadda.accountservice.adapter.in.web.response;

import com.seungse.amadda.accountservice.domain.AuthToken;
import lombok.*;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignInAccountResponse {

    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private long expiresIn;
    private long refreshExpiresIn;

    public static SignInAccountResponse from(AuthToken authToken) {
        return SignInAccountResponse
                .builder()
                .accessToken(authToken.accessToken())
                .refreshToken(authToken.refreshToken())
                .tokenType(authToken.tokenType())
                .expiresIn(authToken.expiresIn())
                .refreshExpiresIn(authToken.refreshExpiresIn())
                .build();
    }
}
