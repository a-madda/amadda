package com.seungse.amadda.adapter.in.web;

import com.seungse.amadda.adapter.in.web.request.CreateRegionGroupRequest;
import com.seungse.amadda.adapter.in.web.response.CreateRegionGroupResponse;
import com.seungse.amadda.application.port.in.CreateRegionGroupUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/region-groups/v1")
@RequiredArgsConstructor
public class RegionGroupController {

    private final CreateRegionGroupUseCase createRegionGroupUseCase;

    @PostMapping
    public ResponseEntity<CreateRegionGroupResponse> create(@RequestBody CreateRegionGroupRequest request) {
        return ResponseEntity.ok(
            createRegionGroupUseCase.createRegionGroup(request.mapToCommand())
                .map(CreateRegionGroupResponse::from)
                .orElseThrow(() -> new IllegalArgumentException("지역 그룹 등록에 실패했습니다."))
        );
    }

}
