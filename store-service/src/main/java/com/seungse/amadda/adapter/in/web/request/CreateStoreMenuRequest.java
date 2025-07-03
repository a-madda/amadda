package com.seungse.amadda.adapter.in.web.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CreateStoreMenuRequest {

    @NotNull
    private String name;

    @NotNull
    private int price;

    @NotNull
    private MultipartFile mainImage;

    @Size(min = 1, max = 3)
    private List<MultipartFile> detailImages;

    @NotNull
    private String description;

    @NotNull
    private String available;

    @NotNull
    private String isRepresentative;

    @NotNull
    private Long storeId;

}
