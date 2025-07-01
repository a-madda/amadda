package com.seungse.amadda.adapter.out.persistance;

import com.seungse.amadda.adapter.out.persistance.entity.RegionGroupEntity;
import com.seungse.amadda.adapter.out.persistance.repository.RegionGroupPostgresRepository;
import com.seungse.amadda.application.port.out.RegionGroupOutPort;
import com.seungse.amadda.domain.RegionGroup;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Geometry;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RegionGroupOutPortAdapter implements RegionGroupOutPort {

    private final RegionGroupPostgresRepository regionGroupPostgresRepository;

    @Override
    public Optional<RegionGroup> createRegionGroup(String name, Geometry union) {
        RegionGroupEntity regionGroupEntity = RegionGroupEntity.builder()
            .name(name)
            .geometry(union)
            .build();

        regionGroupPostgresRepository.save(regionGroupEntity);

        return Optional.of(regionGroupEntity.toDomain());
    }

}
