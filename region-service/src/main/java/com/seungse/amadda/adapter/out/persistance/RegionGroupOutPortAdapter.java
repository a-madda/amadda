package com.seungse.amadda.adapter.out.persistance;

import com.seungse.amadda.adapter.out.persistance.entity.RegionGroupEntity;
import com.seungse.amadda.adapter.out.persistance.entity.RegionGroupGeometryEntity;
import com.seungse.amadda.adapter.out.persistance.repository.RegionGroupGeometryPostgresRepository;
import com.seungse.amadda.adapter.out.persistance.repository.RegionGroupPostgresRepository;
import com.seungse.amadda.application.port.out.RegionGroupOutPort;
import com.seungse.amadda.domain.RegionGroup;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Geometry;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RegionGroupOutPortAdapter implements RegionGroupOutPort {

    private final RegionGroupPostgresRepository regionGroupPostgresRepository;
    private final RegionGroupGeometryPostgresRepository regionGroupGeometryPostgresRepository;

    @Override
    public Optional<RegionGroup> createRegionGroup(String parentName, String parentCode, String name, String code, Geometry geometry) {
        RegionGroupEntity regionGroupEntity = RegionGroupEntity.builder()
            .parentName(parentName)
            .parentCode(parentCode)
            .name(name)
            .code(code)
            .build();
        regionGroupPostgresRepository.save(regionGroupEntity);

        RegionGroupGeometryEntity regionGroupGeometryEntity = RegionGroupGeometryEntity.builder()
            .regionGroupId(regionGroupEntity.getId())
            .geometry(geometry)
            .build();
        regionGroupGeometryPostgresRepository.save(regionGroupGeometryEntity);

        return Optional.of(regionGroupEntity.toDomain());
    }

    @Override
    public List<RegionGroup> findAllRegionGroups() {
        return regionGroupPostgresRepository.findAll()
            .stream()
            .map(RegionGroupEntity::toDomain)
            .toList();
    }

}
