package com.seungse.amadda.application.port.out;

import com.seungse.amadda.domain.Store;

import java.util.Optional;

public interface StoreOutPort {

    Store createStore(Store store);

    Optional<Store> getStore(Long storeId);

    void updateStoreImages(Store store);

}
