package com.seungse.amadda.accountservice.adapter.out.persistence;

import com.seungse.amadda.accountservice.domain.Account;
import com.seungse.amadda.accountservice.domain.AccountRole;
import com.seungse.amadda.accountservice.domain.AccountStatus;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KeyCloakAccount {

    private String accountNo;
    private String email;
    private String phone;
    private AccountStatus accountStatus;
    private Set<AccountRole> roles;
    private String password;

    public static KeyCloakAccount newAccountFrom(Account account) {
        return KeyCloakAccount.builder()
                .accountNo(UUID.randomUUID().toString())
                .email(account.email())
                .phone(account.phone())
                .accountStatus(AccountStatus.PENDING)
                .build();
    }

    public Account toDomain() {
        return Account.builder()
                .accountNo(this.accountNo)
                .email(this.email)
                .phone(this.phone)
                .accountStatus(this.accountStatus)
                .build();
    }

}
