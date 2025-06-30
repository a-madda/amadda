package com.seungse.amadda.adapter.in.web.response;

import com.seungse.amadda.domain.*;
import lombok.Builder;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Builder
public class ReadStoreResponse {

    private Long id;

    private String businessNumber;

    private String storeName;

    private Category category;

    private List<StoreImageResponse> storeImages;

    private String description;

    private String phoneNumber;

    private String address;

    private Set<AmenityType> amenities;

    private StoreHolidayResponse storeHoliday;

    private List<OperatingHourResponse> operatingHours;

    @Getter
    @Builder
    public static class StoreImageResponse {

        private String bucketName;
        private String originalFileName;
        private String filePath;
        private String fileType;
        private Long fileSize;
        private Integer fileOrder;

        public static StoreImageResponse from(StoreImage image) {
            return StoreImageResponse.builder()
                .bucketName(image.getBucketName())
                .originalFileName(image.getOriginalFileName())
                .filePath(image.getFilePath())
                .fileType(image.getFileType())
                .fileSize(image.getFileSize())
                .fileOrder(image.getFileOrder())
                .build();
        }

    }

    @Getter
    @Builder
    public static class StoreHolidayResponse {

        private boolean isHoliday;
        private HolidayType holidayType;
        private Set<DayOfWeek> holidayDays;

        public static StoreHolidayResponse from(StoreHoliday holiday) {
            return StoreHolidayResponse.builder()
                .isHoliday(holiday.isHoliday())
                .holidayType(holiday.getHolidayType())
                .holidayDays(holiday.getHolidayDays())
                .build();
        }

    }

    @Getter
    @Builder
    public static class OperatingHourResponse {

        private OperatingHourType type;
        private DayOfWeek dayOfWeek;
        private LocalTime openTime;
        private LocalTime closeTime;
        private LocalTime breakStartTime;
        private LocalTime breakEndTime;

        public static OperatingHourResponse from(OperatingHour hour) {
            return OperatingHourResponse.builder()
                .type(hour.getType())
                .dayOfWeek(hour.getDayOfWeek())
                .openTime(hour.getOpenTime())
                .closeTime(hour.getCloseTime())
                .breakStartTime(hour.getBreakStartTime())
                .breakEndTime(hour.getBreakEndTime())
                .build();
        }

    }

    public static ReadStoreResponse from(Store store) {
        return ReadStoreResponse.builder()
            .id(store.getId())
            .businessNumber(store.getBusinessNumber())
            .storeName(store.getStoreName())
            .category(store.getCategory())
            .storeImages(
                store.getStoreImages().stream()
                    .map(StoreImageResponse::from)
                    .collect(Collectors.toList())
            )
            .description(store.getDescription())
            .phoneNumber(store.getPhoneNumber())
            .address(store.getAddress())
            .amenities(store.getAmenities())
            .storeHoliday(StoreHolidayResponse.from(store.getStoreHoliday()))
            .operatingHours(store.getOperatingHours().stream()
                                .map(OperatingHourResponse::from)
                                .collect(Collectors.toList()))
            .build();
    }

}
