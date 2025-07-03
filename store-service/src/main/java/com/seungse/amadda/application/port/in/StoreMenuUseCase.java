package com.seungse.amadda.application.port.in;

import com.seungse.amadda.domain.StoreMenu;

import java.util.List;

public interface StoreMenuUseCase {

    List<StoreMenu> getStoreMenus(Long storeId);

    StoreMenu createMenu(CreateMenuCommand command);

    void deleteMenu(Long menuId);

}
