package com.seungse.amadda.application.port.out;

import com.seungse.amadda.domain.StoreMenu;
import com.seungse.amadda.domain.StoreMenuImage;

import java.util.List;
import java.util.Optional;

public interface StoreMenuOutPort {

    StoreMenu createMenu(StoreMenu menu);

    Optional<StoreMenu> findById(Long menuId);

    List<StoreMenu> findByStoreId(Long storeId);

    void deleteById(Long id);

    void updateMenuImages(StoreMenu storeMenu, List<StoreMenuImage> storeMenuImage);

}
