package com.seungse.amadda.adapter.in.web.response;

import lombok.*;

import java.util.List;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetRegionGroupsResponse {

    private Long regionGroupId;
    private String name;
    private List<GetRegionGroupsResponse> regions;

}
