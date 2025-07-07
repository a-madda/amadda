package com.seungse.amadda.adapter.in.web.response;

import com.seungse.amadda.domain.Store;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateStoreResponse {

    private Long id;

    private String businessNumber;

    private String storeName;

    public static CreateStoreResponse toResponse(Store store) {
        return CreateStoreResponse.builder()
            .id(store.getId())
            .businessNumber(store.getBusinessNumber())
            .storeName(store.getStoreName())
            .build();
    }

}
