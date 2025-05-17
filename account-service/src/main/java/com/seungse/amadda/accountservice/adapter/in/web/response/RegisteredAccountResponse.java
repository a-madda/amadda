package com.seungse.amadda.accountservice.adapter.in.web.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.seungse.amadda.accountservice.domain.Account;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisteredAccountResponse {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    private String email;

    private String name;

    private AccountStatus status;

    public static RegisteredAccountResponse from(@NotNull Account account) {
        return RegisteredAccountResponse.builder()
                .accountId(account.id())
                .email(account.email())
                .name(account.name())
                .status(AccountStatus.from(account.accountStatus().name()))
                .build();
    }

}
