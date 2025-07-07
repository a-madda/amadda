package com.seungse.amadda.adapter.out.persistence;

import com.seungse.amadda.adapter.out.persistence.entity.StoreEntity;
import com.seungse.amadda.adapter.out.persistence.entity.StoreImageEntity;
import com.seungse.amadda.adapter.out.persistence.mapper.StoreDomainMapper;
import com.seungse.amadda.adapter.out.persistence.repository.StoreRepository;
import com.seungse.amadda.application.port.out.StoreOutPort;
import com.seungse.amadda.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class StoreOutPortAdapter implements StoreOutPort {

    private final StoreRepository storeRepository;

    @Override
    public Store createStore(Store store) {
        StoreEntity savedStore = storeRepository.save(StoreDomainMapper.toEntity(store));

        return StoreDomainMapper.toDomain(savedStore);
    }

    @Override
    public Optional<Store> getStore(Long storeId) {
        return storeRepository.findById(storeId)
            .map(StoreDomainMapper::toDomain);
    }

    @Override
    public void updateStoreImages(Store store) {
        StoreEntity entity = storeRepository.findById(store.getId())
            .orElseThrow(() -> new IllegalArgumentException("Store not found"));

        List<StoreImageEntity> storeImages = store.getStoreImages().stream()
            .map(StoreImageEntity::from)
            .collect(Collectors.toList());
        storeImages.forEach(image -> image.setStore(entity));

        entity.setStoreImages(storeImages);
        storeRepository.save(entity);
    }

}
