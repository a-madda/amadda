package com.seungse.amadda.accountservice.infrastructor.advice.exceptions;

public class NotMatchedPasswordException extends AccountException{

    public NotMatchedPasswordException() {
        super(ErrorCode.NOT_MATCHED_PASSWORD);
    }

}
