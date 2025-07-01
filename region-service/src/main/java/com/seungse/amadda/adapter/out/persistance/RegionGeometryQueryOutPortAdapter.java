package com.seungse.amadda.adapter.out.persistance;

import com.seungse.amadda.adapter.out.persistance.repository.RegionGeometryPostgresRepository;
import com.seungse.amadda.application.port.out.RegionGeometryQueryOutPort;
import com.seungse.amadda.domain.RegionGeometry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Geometry;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegionGeometryQueryOutPortAdapter implements RegionGeometryQueryOutPort {

    private final RegionGeometryPostgresRepository regionGeometryPostgresRepository;

    @Override
    public List<Geometry> findGeometriesByCodes(List<String> regionCodes) {
        List<RegionGeometry> regionGeometries = regionGeometryPostgresRepository.findByCodeIn(regionCodes);

        return regionGeometries.stream().map(RegionGeometry::getGeometry).toList();
    }

}
