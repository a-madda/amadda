package com.seungse.amadda.accountservice.application.service;

import com.seungse.amadda.accountservice.application.port.in.SignInAccountCommandV1;
import com.seungse.amadda.accountservice.application.port.in.SignInAccountUseCaseV1;
import com.seungse.amadda.accountservice.application.port.out.AccountValidationOutPortV1;
import com.seungse.amadda.accountservice.application.port.out.SignInAccountOutPortV1;
import com.seungse.amadda.accountservice.domain.AuthToken;
import com.seungse.amadda.accountservice.infrastructor.advice.exceptions.AccountException;
import com.seungse.amadda.accountservice.infrastructor.annotations.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class SignInAccountServiceV1 implements SignInAccountUseCaseV1 {

    private final AccountValidationOutPortV1 accountValidationOutPort;
    private final SignInAccountOutPortV1 signInAccountOutPortV1;

    @Override
    public Optional<AuthToken> loginAccount(SignInAccountCommandV1 signInAccountCommandV1) {
        if (accountValidationOutPort.isNotExistAccount(signInAccountCommandV1.getEmail())) {
            log.info("Email already exist :{}", signInAccountCommandV1.getEmail());
            throw new AccountException();
        }

        return signInAccountOutPortV1.signInAccount(signInAccountCommandV1.getEmail(), signInAccountCommandV1.getPassword());
    }
}
