package com.seungse.amadda.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AmenityType {
    PARKING("주차 가능"),
    MICHELIN("미쉐린 선정"),
    CORKAGE("콜키지 가능"),
    CORKAGE_FREE("콜키지 프리"),
    GROUP_OK("단체 이용 가능"),
    SOLO_OK("혼밥 가능"),
    PET_FRIENDLY("반려동물 동반"),
    WIFI("와이파이 제공");

    private final String amenityName;
}
