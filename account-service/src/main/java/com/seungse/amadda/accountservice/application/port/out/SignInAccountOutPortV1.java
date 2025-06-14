package com.seungse.amadda.accountservice.application.port.out;

import com.seungse.amadda.accountservice.domain.AuthToken;

import java.util.Optional;

public interface SignInAccountOutPortV1 {
    Optional<AuthToken> signInAccount(String email, String password);
}
