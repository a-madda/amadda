package com.seungse.amadda.adapter.out.persistance.repository;

import com.seungse.amadda.adapter.out.persistance.entity.RegionGeometryEntity;
import com.seungse.amadda.infrastructure.repository.RegionGeometryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionGeometryPostgresRepository
    extends RegionGeometryRepository<RegionGeometryEntity, String>, JpaRepository<RegionGeometryEntity, String> {

}
