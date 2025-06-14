package com.seungse.amadda.accountservice.domain;

import lombok.Builder;

@Builder
public record Account(
        Long id,
        String accountNo,
        String email,
        String phone,
        String password,
        AccountStatus accountStatus
) {

}
