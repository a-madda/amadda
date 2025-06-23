package com.seungse.amadda.adapter.in.web.request;

import com.seungse.amadda.application.port.in.CreateRegionGroupCommand;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
public class CreateRegionGroupRequest {

    /**
     * 지역 그룹 이름
     */
    private String name;
    /**
     * 지역 코드 목록
     */
    private List<String> regionCodes;

    public CreateRegionGroupCommand mapToCommand() {
        return CreateRegionGroupCommand.builder()
                .name(name)
                .regionCodes(regionCodes)
                .build();
    }

}
