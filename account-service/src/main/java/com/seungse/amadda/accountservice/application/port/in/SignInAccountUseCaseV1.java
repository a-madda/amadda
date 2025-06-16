package com.seungse.amadda.accountservice.application.port.in;

import com.seungse.amadda.accountservice.domain.AuthToken;

import java.util.Optional;

public interface SignInAccountUseCaseV1 {

    Optional<AuthToken> loginAccount(SignInAccountCommandV1 signInAccountCommandV1);
}
