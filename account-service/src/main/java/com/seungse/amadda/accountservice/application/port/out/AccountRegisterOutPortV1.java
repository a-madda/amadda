package com.seungse.amadda.accountservice.application.port.out;

import com.seungse.amadda.accountservice.domain.Account;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public interface AccountRegisterOutPortV1 {

    Optional<Account> createAccount(Account account, @NotNull String password);

    boolean isExistAccount(@Email String email);
}
