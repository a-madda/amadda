package com.seungse.amadda.infrastructure.exceptions;

public class StoreNotFoundException extends StoreException {

    public StoreNotFoundException() {
        super(ErrorCode.STORE_NOT_FOUND);
    }

}
