package com.seungse.amadda.adapter.out.persistence.redis.model;

import com.seungse.amadda.application.port.in.CreateStoreBasicCommand;
import com.seungse.amadda.domain.Category;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreBasicInfo implements Serializable {

    private String storeBasicKey;
    private String businessNumber;
    private String storeName;
    private Category category;
    private String description;
    private String phoneNumber;
    private String address;

    public static StoreBasicInfo from(CreateStoreBasicCommand command) {
        return StoreBasicInfo.builder()
            .storeBasicKey(command.getStoreBasicKey())
            .businessNumber(command.getBusinessNumber())
            .storeName(command.getStoreName())
            .category(command.getCategory())
            .description(command.getDescription())
            .phoneNumber(command.getPhoneNumber())
            .address(command.getAddress())
            .build();
    }

}
