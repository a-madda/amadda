package com.seungse.amadda.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OperatingHourType {
    ALL_DAYS("모든 영업일 같음"),
    WEEKDAY_WEEKEND("평일/주말 다름"),
    BY_DAY("요일별로 다름");

    private final String operatingHourType;
}
