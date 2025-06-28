package com.seungse.amadda.adapter.out.persistence.mapper;

import com.seungse.amadda.adapter.out.persistence.entity.*;
import com.seungse.amadda.domain.AmenityType;
import com.seungse.amadda.domain.Store;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StoreDomainMapper {

    public static StoreEntity toEntity(Store store) {
        StoreEntity entity = StoreEntity.builder()
            .id(store.getId())
            .businessNumber(store.getBusinessNumber())
            .storeName(store.getStoreName())
            .category(store.getCategory())
            .description(store.getDescription())
            .phoneNumber(store.getPhoneNumber())
            .address(store.getAddress())
            .build();

        List<StoreImageEntity> storeImages = store.getStoreImages().stream()
            .map(StoreImageEntity::from)
            .collect(Collectors.toList());
        storeImages.forEach(image -> image.setStore(entity));
        entity.setStoreImages(storeImages);

        Set<StoreAmenityEntity> amenities = store.getAmenities().stream()
            .map(name -> {
                StoreAmenityEntity amenity = StoreAmenityEntity.of(String.valueOf(name));
                amenity.setStore(entity);
                return amenity;
            })
            .collect(Collectors.toSet());
        entity.setAmenities(amenities);

        List<StoreOperatingHourEntity> operatingHours = store.getOperatingHours().stream()
            .map(operatingHour -> {
                StoreOperatingHourEntity storeOperatingHour = StoreOperatingHourEntity.from(operatingHour);
                storeOperatingHour.setStore(entity);
                return storeOperatingHour;
            })
            .collect(Collectors.toList());
        entity.setOperatingHours(operatingHours);

        if (store.getStoreHoliday() != null) {
            StoreHolidayEntity holidayEntity = StoreHolidayEntity.from(store.getStoreHoliday());
            holidayEntity.setStore(entity);

            Set<StoreHolidayDayEntity> holidayDays = store.getStoreHoliday().getHolidayDays().stream()
                .map(dayOfWeek -> StoreHolidayDayEntity.builder()
                    .dayOfWeek(dayOfWeek)
                    .storeHoliday(holidayEntity) // 여기서 설정
                    .build())
                .collect(Collectors.toSet());

            holidayEntity.setHolidayDays(holidayDays);
            entity.setStoreHoliday(holidayEntity);
        }

        return entity;
    }

    public static Store toDomain(StoreEntity storeEntity) {
        return Store.builder()
            .id(storeEntity.getId())
            .businessNumber(storeEntity.getBusinessNumber())
            .storeName(storeEntity.getStoreName())
            .category(storeEntity.getCategory())
            .storeImages(
                storeEntity.getStoreImages().stream()
                    .map(StoreImageEntity::toDomain)
                    .collect(Collectors.toList())
            )
            .description(storeEntity.getDescription())
            .phoneNumber(storeEntity.getPhoneNumber())
            .address(storeEntity.getAddress())
            .amenities(
                storeEntity.getAmenities().stream()
                    .map(amenityEntity -> AmenityType.valueOf(amenityEntity.getName().toUpperCase()))
                    .collect(Collectors.toSet())
            )
            .storeHoliday(
                storeEntity.getStoreHoliday() != null ? storeEntity.getStoreHoliday().toDomain() : null
            )
            .operatingHours(
                storeEntity.getOperatingHours().stream()
                    .map(StoreOperatingHourEntity::toDomain)
                    .collect(Collectors.toList())
            )
            .build();
    }

}
