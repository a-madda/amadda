package com.seungse.amadda.accountservice.infrastructor.advice.exceptions;

public class AlreadyExistEmailException extends AccountException {

    public AlreadyExistEmailException() {
        super(ErrorCode.EXIST_EMAIL);
    }

}
