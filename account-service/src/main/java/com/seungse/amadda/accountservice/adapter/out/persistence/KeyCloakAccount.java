package com.seungse.amadda.accountservice.adapter.out.persistence;

import com.seungse.amadda.accountservice.domain.Account;
import com.seungse.amadda.accountservice.domain.AccountRole;
import com.seungse.amadda.accountservice.domain.AccountStatus;
import lombok.*;
import org.keycloak.representations.AccessTokenResponse;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KeyCloakAccount {

    private String email;
    private String phone;
    private AccountStatus accountStatus;
    private Set<AccountRole> roles;
    private String password;
    private String refreshToken;
    private String token;
    private long expiresIn;
    private long refreshExpiresIn;

    /**
     * TODO accountStatus의 경우 이메일 인증을 통해서 ACTIVE 처리할 수 있도록 변경 필요
     *
     * @param account
     * @return
     */
    public static KeyCloakAccount newAccountFrom(Account account) {
        return KeyCloakAccount.builder()
                .email(account.email())
                .phone(account.phone())
                .accountStatus(AccountStatus.ACTIVE)
                .build();
    }

    public Account toDomain() {
        return Account.builder()
                .email(this.email)
                .phone(this.phone)
                .accountStatus(this.accountStatus)
                .build();
    }

    public void updateAccessToken(AccessTokenResponse accessTokenResponse) {
        this.refreshToken = accessTokenResponse.getRefreshToken();
        this.token = accessTokenResponse.getToken();
        this.expiresIn = accessTokenResponse.getExpiresIn();
        this.refreshExpiresIn = accessTokenResponse.getRefreshExpiresIn();
    }
}
