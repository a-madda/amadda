package com.seungse.amadda.adapter.in.web.mapper;

import com.seungse.amadda.adapter.in.web.response.GetRegionGroupsResponse;
import com.seungse.amadda.domain.RegionGroup;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 지역 그룹 도메인 모델을 API 응답 형식인 {@link GetRegionGroupsResponse}로 변환하는 매퍼 클래스입니다.
 */
public class RegionGroupsResponseMapper {

    private static final String ROOT_PARENT_CODE = "ROOT";

    /**
     * 지역 그룹 리스트를 응답 객체 리스트로 변환합니다.
     */
    public static List<GetRegionGroupsResponse> toResponse(List<RegionGroup> regionGroups) {
        Map<String, List<RegionGroup>> groupedByParent = regionGroups.stream()
            .collect(Collectors.groupingBy(rg -> Optional.ofNullable(rg.getParentCode())
                .filter(code -> !code.isBlank())
                .orElse(ROOT_PARENT_CODE)
            ));

        return buildHierarchy(ROOT_PARENT_CODE, groupedByParent);
    }

    /**
     * 재귀적으로 지역 그룹 계층 구조를 구성합니다.
     */
    private static List<GetRegionGroupsResponse> buildHierarchy(String parentCode, Map<String, List<RegionGroup>> grouped) {
        List<RegionGroup> children = grouped.getOrDefault(parentCode, Collections.emptyList());

        return children.stream()
            .map(group -> GetRegionGroupsResponse.builder()
                .regionGroupId(group.getRegionGroupId())
                .name(group.getName())
                .regions(buildHierarchy(group.getCode(), grouped)) // 자식 노드 재귀 호출
                .build())
            .collect(Collectors.toList());
    }

}