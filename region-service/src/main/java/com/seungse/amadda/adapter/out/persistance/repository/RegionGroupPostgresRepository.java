package com.seungse.amadda.adapter.out.persistance.repository;

import com.seungse.amadda.adapter.out.persistance.entity.RegionGroupEntity;
import com.seungse.amadda.infrastructure.repository.RegionGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionGroupPostgresRepository extends RegionGroupRepository<RegionGroupEntity, Long>, JpaRepository<RegionGroupEntity, Long> {

}
