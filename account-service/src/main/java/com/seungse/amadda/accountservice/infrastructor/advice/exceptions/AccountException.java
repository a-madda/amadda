package com.seungse.amadda.accountservice.infrastructor.advice.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccountException extends RuntimeException{

    private String errorCode;
    private String errorMessage;

    public AccountException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getMessage();
    }

}
