package com.seungse.amadda.adapter.in.web;

import com.seungse.amadda.adapter.in.web.mapper.RegionGroupsResponseMapper;
import com.seungse.amadda.adapter.in.web.request.CreateRegionGroupRequest;
import com.seungse.amadda.adapter.in.web.response.CreateRegionGroupResponse;
import com.seungse.amadda.adapter.in.web.response.GetRegionGroupsResponse;
import com.seungse.amadda.application.port.in.CreateRegionGroupUseCase;
import com.seungse.amadda.application.port.in.GetRegionGroupsUseCase;
import com.seungse.amadda.domain.RegionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region-groups/v1")
@RequiredArgsConstructor
public class RegionGroupController {

    private final CreateRegionGroupUseCase createRegionGroupUseCase;
    private final GetRegionGroupsUseCase getRegionGroupsUseCase;

    @GetMapping
    public ResponseEntity<List<GetRegionGroupsResponse>> getRegionGroups() {
        List<RegionGroup> regionGroups = getRegionGroupsUseCase.getRegionGroups();
        return ResponseEntity.ok(RegionGroupsResponseMapper.toResponse(regionGroups));
    }

    @PostMapping
    public ResponseEntity<CreateRegionGroupResponse> create(@RequestBody CreateRegionGroupRequest request) {
        return ResponseEntity.ok(
            createRegionGroupUseCase.createRegionGroup(request.mapToCommand())
                .map(CreateRegionGroupResponse::from)
                .orElseThrow(() -> new IllegalArgumentException("지역 그룹 등록에 실패했습니다."))
        );
    }

}
