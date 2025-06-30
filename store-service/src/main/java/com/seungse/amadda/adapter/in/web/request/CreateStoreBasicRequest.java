package com.seungse.amadda.adapter.in.web.request;

import com.seungse.amadda.application.port.in.CreateStoreBasicCommand;
import com.seungse.amadda.domain.Category;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class CreateStoreBasicRequest {

    @NotNull
    private String businessNumber;

    @NotNull
    private String storeName;

    @NotNull
    private Category category;

    private String description;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String address;

    public CreateStoreBasicCommand mapToCommand() {
        return CreateStoreBasicCommand.builder()
            .businessNumber(businessNumber)
            .storeName(storeName)
            .category(category)
            .description(description)
            .phoneNumber(phoneNumber)
            .address(address)
            .build();
    }

}
