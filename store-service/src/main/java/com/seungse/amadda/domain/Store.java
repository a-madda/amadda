package com.seungse.amadda.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Builder
public class Store {

    private Long id;

    private String businessNumber;

    private String storeName;

    private Category category;

    private List<StoreImage> storeImages;

    private String description;

    private String phoneNumber;

    private String address;

    private Set<AmenityType> amenities;

    private StoreHoliday storeHoliday;

    private List<OperatingHour> operatingHours;

    public void setStoreImages(List<StoreImage> images) {
        this.storeImages = images != null ? images : new ArrayList<>();
    }

}
