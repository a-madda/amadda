package com.seungse.amadda.application.port.in;

import com.seungse.amadda.domain.RegionGroup;

import java.util.List;

public interface GetRegionGroupsUseCase {

    List<RegionGroup> getRegionGroups();

}
