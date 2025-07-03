package com.seungse.amadda.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class StoreMenu {

    private Long id;

    private String name;

    private int price;

    private List<StoreMenuImage> images;

    private String description;

    private String available;

    private String isRepresentative;

    private Long storeId;

    public void setImages(List<StoreMenuImage> images) {
        this.images = images != null ? images : new ArrayList<>();
    }

}
