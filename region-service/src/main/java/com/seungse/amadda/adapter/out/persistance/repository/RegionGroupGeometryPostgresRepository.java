package com.seungse.amadda.adapter.out.persistance.repository;

import com.seungse.amadda.adapter.out.persistance.entity.RegionGroupGeometryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionGroupGeometryPostgresRepository extends JpaRepository<RegionGroupGeometryEntity, Long> {

}
