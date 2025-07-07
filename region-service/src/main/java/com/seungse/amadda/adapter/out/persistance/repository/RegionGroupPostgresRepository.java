package com.seungse.amadda.adapter.out.persistance.repository;

import com.seungse.amadda.adapter.out.persistance.entity.RegionGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionGroupPostgresRepository extends JpaRepository<RegionGroupEntity, Long> {

}
