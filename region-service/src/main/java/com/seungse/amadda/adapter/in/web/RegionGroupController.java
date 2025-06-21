package com.seungse.amadda.adapter.in.web;

import com.seungse.amadda.adapter.in.web.request.CreateRegionGroupRequest;
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
    public ResponseEntity<Void> create(@RequestBody CreateRegionGroupRequest request) {
        createRegionGroupUseCase.createRegionGroup(request.mapToCommand());
        return ResponseEntity.ok().build();
    }

}
