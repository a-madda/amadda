package com.seungse.amadda.infrastructure.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StoreException extends RuntimeException {

    private String errorCode;
    private String errorMessage;

    public StoreException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getMessage();
    }

}
