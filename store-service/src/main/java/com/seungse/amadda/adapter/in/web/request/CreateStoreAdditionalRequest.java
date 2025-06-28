package com.seungse.amadda.adapter.in.web.request;

import com.seungse.amadda.domain.AmenityType;
import com.seungse.amadda.domain.HolidayType;
import com.seungse.amadda.domain.OperatingHourType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Getter
@ToString
@EqualsAndHashCode
public class CreateStoreAdditionalRequest {

    private String storeBasicKey;

    //@Size(min = 1, max = 20)
    private List<MultipartFile> storeImages;

    private Set<AmenityType> amenities;

    private StoreHolidayRequest storeHoliday;

    private List<OperatingHourRequest> operatingHours;

    @Getter
    public static class StoreHolidayRequest {

        private boolean isHoliday;
        private HolidayType holidayType;
        private Set<DayOfWeek> holidayDays;

    }

    @Getter
    public static class OperatingHourRequest {

        private OperatingHourType type;

        private DayOfWeek dayOfWeek;

        private LocalTime openTime;

        private LocalTime closeTime;

        private LocalTime breakStartTime;

        private LocalTime breakEndTime;

    }

    public void setImages(List<MultipartFile> files) {
        this.storeImages = files;
    }

}
