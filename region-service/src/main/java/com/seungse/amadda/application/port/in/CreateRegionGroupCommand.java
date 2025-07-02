package com.seungse.amadda.application.port.in;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * 지역 그룹 등록 커맨드
 */
@Getter
@Builder
public class CreateRegionGroupCommand {
    private final String parentName;
    private final String parentCode;
    private final String name;
    private final String code;
    private final List<String> regionCodes;
}
