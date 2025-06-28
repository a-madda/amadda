package com.seungse.amadda.adapter.out.persistence.repository;

import com.seungse.amadda.adapter.out.persistence.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

}
