package com.seungse.amadda.accountservice.adapter.out.persistence;

import com.seungse.amadda.accountservice.domain.Account;
import com.seungse.amadda.accountservice.domain.AccountRole;
import com.seungse.amadda.accountservice.domain.AccountStatus;
import com.seungse.amadda.accountservice.infrastructor.generator.IdGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountEntity {

    @Id
    @IdGenerator
    private Long id;
    private String accountNo;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    @Enumerated(EnumType.STRING)
    private Set<AccountRole> roles;
    private String password;

    public void setEncryptedPassword(String password) {
        this.password = password;
    }

    public static AccountEntity newEntityFrom(Account account) {
        return AccountEntity.builder()
                .id(account.id())
                .accountNo(UUID.randomUUID().toString())
                .email(account.email())
                .phone(account.phone())
                .accountStatus(AccountStatus.PENDING)
                .build();
    }

    public Account toDomain() {
        return Account.builder()
                .id(this.id)
                .accountNo(this.accountNo)
                .email(this.email)
                .phone(this.phone)
                .accountStatus(this.accountStatus)
                .build();
    }

}
