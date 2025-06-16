package com.seungse.amadda.accountservice.adapter.in.web.request;

import com.seungse.amadda.accountservice.application.port.in.SignInAccountCommandV1;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class SignInRequest {

    private String email;

    @NotNull
    private String password;

    public SignInAccountCommandV1 mapToCommand() {
        return SignInAccountCommandV1.builder().email(email).password(password).build();
    }
}
