package com.seungse.amadda.accountservice.application.service;

import com.seungse.amadda.accountservice.application.port.in.AccountRegisterCommandV1;
import com.seungse.amadda.accountservice.application.port.in.AccountRegisterUseCaseV1;
import com.seungse.amadda.accountservice.application.port.out.AccountRegisterOutPortV1;
import com.seungse.amadda.accountservice.domain.Account;
import com.seungse.amadda.accountservice.infrastructor.annotations.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

/**
 * 사용자 등록 서비스 Ver.1
 * @author seungse
 */
@UseCase
@RequiredArgsConstructor
public class AccountRegisterServiceV1 implements AccountRegisterUseCaseV1 {

    private final AccountRegisterOutPortV1 accountRegisterOutPort;

    @Override
    public Optional<Account> registerAccount(AccountRegisterCommandV1 command) {
        return accountRegisterOutPort.createAccount(command.mapToDomain(), command.getPassword());
    }

}
