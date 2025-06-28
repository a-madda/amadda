package com.seungse.amadda.application.port.in;

import com.seungse.amadda.adapter.out.persistence.redis.model.StoreBasicInfo;
import com.seungse.amadda.domain.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateStoreBasicCommand {

    private String businessNumber;
    private String storeName;
    private Category category;
    private String description;
    private String phoneNumber;
    private String address;

    public static CreateStoreBasicCommand mapToCommand(StoreBasicInfo storeBasicInfo) {
        return CreateStoreBasicCommand.builder()
            .businessNumber(storeBasicInfo.getBusinessNumber())
            .storeName(storeBasicInfo.getStoreName())
            .category(storeBasicInfo.getCategory())
            .description(storeBasicInfo.getDescription())
            .phoneNumber(storeBasicInfo.getPhoneNumber())
            .address(storeBasicInfo.getAddress())
            .build();
    }

}
