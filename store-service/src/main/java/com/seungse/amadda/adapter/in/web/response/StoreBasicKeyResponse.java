package com.seungse.amadda.adapter.in.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoreBasicKeyResponse {

    private String storeBasicKey;

    public static StoreBasicKeyResponse of(String storeBasicKey) {
        return new StoreBasicKeyResponse(storeBasicKey);
    }

}
