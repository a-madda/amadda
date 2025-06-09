package com.seungse.amadda.accountservice.application.service;

import com.seungse.amadda.accountservice.application.port.in.AccountRegisterCommandV1;
import com.seungse.amadda.accountservice.application.port.in.AccountRegisterUseCaseV1;
import com.seungse.amadda.accountservice.application.port.out.AccountRegisterOutPortV1;
import com.seungse.amadda.accountservice.domain.Account;
import com.seungse.amadda.accountservice.infrastructor.annotations.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * 사용자 등록 서비스 Ver.1
 *
 * @author seungse
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class AccountRegisterServiceV1 implements AccountRegisterUseCaseV1 {

    private final AccountRegisterOutPortV1 accountRegisterOutPort;

    @Override
    public Optional<Account> registerAccount(AccountRegisterCommandV1 command) {
        if (accountRegisterOutPort.isExistAccount(command.getEmail())) {
            log.info("Email already exist :{}", command.getEmail());
//            throw new IllegalAccessException("이미 가입한 이메일 계정이 있습니다.");
            return Optional.empty();
        }
        return accountRegisterOutPort.createAccount(command.mapToDomain(), command.getPassword());
    }

}
