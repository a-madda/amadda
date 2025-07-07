package com.seungse.amadda.infrastructure.exceptions;

public class StoreBasicKeyNotFoundException extends StoreException {

    public StoreBasicKeyNotFoundException() {
        super(ErrorCode.STORE_BASIC_KEY_NOT_FOUND);
    }

}
