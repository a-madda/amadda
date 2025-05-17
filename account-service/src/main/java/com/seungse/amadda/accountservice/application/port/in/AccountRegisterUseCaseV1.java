package com.seungse.amadda.accountservice.application.port.in;

import com.seungse.amadda.accountservice.domain.Account;

import java.util.Optional;

public interface AccountRegisterUseCaseV1 {

    Optional<Account> registerAccount(AccountRegisterCommandV1 accountRegisterCommandV1);

}
