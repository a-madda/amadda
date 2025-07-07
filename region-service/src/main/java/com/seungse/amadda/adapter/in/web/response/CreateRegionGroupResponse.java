package com.seungse.amadda.adapter.in.web.response;

import com.seungse.amadda.domain.RegionGroup;
import lombok.*;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateRegionGroupResponse {

    private Long regionGroupId;
    private String name;
    public static  CreateRegionGroupResponse from(RegionGroup regionGroup) {
        return CreateRegionGroupResponse.builder()
                .regionGroupId(regionGroup.getRegionGroupId())
                .name(regionGroup.getName())
                .build();
    }

}
