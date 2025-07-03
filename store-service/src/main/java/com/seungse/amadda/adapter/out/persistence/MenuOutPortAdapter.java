package com.seungse.amadda.adapter.out.persistence;

import com.seungse.amadda.adapter.out.persistence.entity.StoreEntity;
import com.seungse.amadda.adapter.out.persistence.entity.StoreMenuEntity;
import com.seungse.amadda.adapter.out.persistence.entity.StoreMenuImageEntity;
import com.seungse.amadda.adapter.out.persistence.repository.MenuImageRepository;
import com.seungse.amadda.adapter.out.persistence.repository.MenuRepository;
import com.seungse.amadda.adapter.out.persistence.repository.StoreRepository;
import com.seungse.amadda.application.port.out.StoreMenuOutPort;
import com.seungse.amadda.domain.StoreMenu;
import com.seungse.amadda.domain.StoreMenuImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MenuOutPortAdapter implements StoreMenuOutPort {

    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final MenuImageRepository menuImageRepository;

    @Override
    public StoreMenu createMenu(StoreMenu menu) {
        StoreEntity storeEntity = storeRepository.findById(menu.getStoreId())
            .orElseThrow(() -> new IllegalArgumentException("Store not found: " + menu.getStoreId()));

        StoreMenuEntity menuEntity = menuRepository.save(StoreMenuEntity.from(menu, storeEntity));
        return menuEntity.toDomain();
    }

    @Override
    public void updateMenuImages(StoreMenu storeMenu, List<StoreMenuImage> menuImages) {
        StoreMenuEntity menuEntity = menuRepository.findById(storeMenu.getId())
            .orElseThrow(() -> new IllegalArgumentException("Menu not found: " + storeMenu.getId()));

        List<StoreMenuImageEntity> storeMenuImage = new ArrayList<>();
        for (StoreMenuImage image : menuImages) {
            StoreMenuImageEntity imageEntity = StoreMenuImageEntity.from(image);
            imageEntity.setStoreMenu(menuEntity);
            storeMenuImage.add(imageEntity);
        }
        menuImageRepository.saveAll(storeMenuImage);

        List<StoreMenuImage> savedImages = storeMenuImage.stream()
            .map(StoreMenuImageEntity::toDomain)
            .toList();
        storeMenu.setImages(savedImages);
    }

    @Override
    public Optional<StoreMenu> findById(Long menuId) {
        return Optional.empty();
    }

    @Override
    public List<StoreMenu> findByStoreId(Long storeId) {
        return menuRepository.findByStoreId(storeId)
            .stream()
            .map(StoreMenuEntity::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {

    }

}
