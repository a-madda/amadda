package com.seungse.amadda.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.DayOfWeek;
import java.util.Set;

@Getter
@Builder
public class StoreHoliday {

    private boolean isHoliday;

    private HolidayType holidayType;

    private Set<DayOfWeek> holidayDays;

}
