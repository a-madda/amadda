package com.seungse.amadda.application.port.in;

import com.seungse.amadda.domain.AmenityType;
import com.seungse.amadda.domain.HolidayType;
import com.seungse.amadda.domain.OperatingHourType;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class CreateStoreAdditionalCommand {

    private List<StoreImageCommand> storeImages;

    private Set<AmenityType> amenities;

    private StoreHolidayCommand storeHoliday;

    private List<OperatingHourCommand> operatingHours;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class StoreImageCommand {

        private String bucketName;
        private String originalFileName;
        private String filePath;
        private String fileType;
        private Long fileSize;
        private Integer fileOrder;

    }

    @Builder
    @Getter
    public static class StoreHolidayCommand {

        private boolean isHoliday;
        private HolidayType holidayType;
        private Set<DayOfWeek> holidayDays;

    }

    @Builder
    @Getter
    public static class OperatingHourCommand {

        private OperatingHourType type;

        private DayOfWeek dayOfWeek;

        private LocalTime openTime;

        private LocalTime closeTime;

        private LocalTime breakStartTime;

        private LocalTime breakEndTime;

    }

}
