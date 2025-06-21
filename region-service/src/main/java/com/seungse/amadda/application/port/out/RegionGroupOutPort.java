package com.seungse.amadda.application.port.out;

import org.locationtech.jts.geom.Geometry;

public interface RegionGroupOutPort {

    void createRegionGroup(String name, Geometry union);

}
