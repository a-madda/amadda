package com.seungse.amadda.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HolidayType {
    WEEKLY("매주"),
    BIWEEKLY("격주"),
    MONTHLY("매월");

    private final String holidayType;
}
