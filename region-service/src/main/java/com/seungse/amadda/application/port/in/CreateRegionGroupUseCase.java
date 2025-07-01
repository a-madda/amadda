package com.seungse.amadda.application.port.in;

import com.seungse.amadda.domain.RegionGroup;

import java.util.Optional;

public interface CreateRegionGroupUseCase {

    Optional<RegionGroup> createRegionGroup(CreateRegionGroupCommand command);

}
