package com.seungse.amadda.adapter.in.web.response;

import com.seungse.amadda.adapter.out.persistence.redis.model.StoreBasicInfo;
import com.seungse.amadda.domain.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoreBasicKeyResponse {

    private String storeBasicKey;

    private String businessNumber;

    private String storeName;

    private Category category;

    private String description;

    private String phoneNumber;

    private String address;

    public static StoreBasicKeyResponse from(StoreBasicInfo info) {
        return StoreBasicKeyResponse.builder()
            .storeBasicKey(info.getStoreBasicKey())
            .businessNumber(info.getBusinessNumber())
            .storeName(info.getStoreName())
            .category(info.getCategory())
            .description(info.getDescription())
            .phoneNumber(info.getPhoneNumber())
            .address(info.getAddress())
            .build();
    }

}
