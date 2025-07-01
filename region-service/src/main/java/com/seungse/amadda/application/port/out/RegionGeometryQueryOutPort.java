package com.seungse.amadda.application.port.out;

import org.locationtech.jts.geom.Geometry;

import java.util.List;

public interface RegionGeometryQueryOutPort {

    List<Geometry> findGeometriesByCodes(List<String> regionCodes);

}
