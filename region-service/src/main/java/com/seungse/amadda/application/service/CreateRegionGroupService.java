package com.seungse.amadda.application.service;

import com.seungse.amadda.application.port.in.CreateRegionGroupCommand;
import com.seungse.amadda.application.port.in.CreateRegionGroupUseCase;
import com.seungse.amadda.application.port.out.RegionGeometryQueryOutPort;
import com.seungse.amadda.application.port.out.RegionGroupOutPort;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateRegionGroupService implements CreateRegionGroupUseCase {

    private final RegionGeometryQueryOutPort regionGeometryQueryOutPort;
    private final RegionGroupOutPort regionGroupOutPort;
    private final GeometryFactory geometryFactory = new GeometryFactory();

    @Override
    public void createRegionGroup(CreateRegionGroupCommand command) {
        // 3뎁스에서만, 인접한 지역만 그룹 생성할 수 있도록 조건 추가 필요

        List<Geometry> geometries = regionGeometryQueryOutPort.findGeometriesByCodes(command.getRegionCodes());
        Geometry union = mergeGeometries(geometries);

        regionGroupOutPort.createRegionGroup(command.getName(), union);
    }

    private Geometry mergeGeometries(List<Geometry> geometries) {
        Geometry result = geometries.getFirst();
        for (int i = 1; i < geometries.size(); i++) {
            result = result.union(geometries.get(i));
        }
        if (!(result instanceof MultiPolygon)) {
            result = geometryFactory.createMultiPolygon(new Polygon[]{(Polygon) result});
        }
        return result;
    }
}
