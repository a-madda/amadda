package com.seungse.amadda.application.port.out;

import com.seungse.amadda.domain.RegionGroup;
import org.locationtech.jts.geom.Geometry;

import java.util.Optional;

public interface RegionGroupOutPort {

    Optional<RegionGroup> createRegionGroup(String name, Geometry union);

}
