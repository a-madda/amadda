package com.seungse.amadda.adapter.out.persistence.repository;

import com.seungse.amadda.adapter.out.persistence.entity.StoreMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<StoreMenuEntity, Long> {

    List<StoreMenuEntity> findByStoreId(Long storeId);

}
