package com.seungse.amadda.application.service;

import com.seungse.amadda.application.port.in.GetRegionGroupsUseCase;
import com.seungse.amadda.application.port.out.RegionGroupOutPort;
import com.seungse.amadda.domain.RegionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetRegionGroupsService implements GetRegionGroupsUseCase {

    private final RegionGroupOutPort regionGroupOutPort;

    @Override
    public List<RegionGroup> getRegionGroups() {
        return regionGroupOutPort.findAllRegionGroups();
    }

}
