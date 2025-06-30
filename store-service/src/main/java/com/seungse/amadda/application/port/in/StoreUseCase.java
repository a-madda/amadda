package com.seungse.amadda.application.port.in;

import com.seungse.amadda.adapter.out.persistence.redis.model.StoreBasicInfo;
import com.seungse.amadda.domain.Store;

import java.util.List;
import java.util.Optional;

public interface StoreUseCase {

    String saveBasicInfoToRedis(CreateStoreBasicCommand command);

    Store createStore(CreateStoreBasicCommand basicCommand, CreateStoreAdditionalCommand additionalCommand);

    Optional<StoreBasicInfo> getRedisStore(String key);

    Optional<Store> getStore(Long storeId);

    void saveStoreImages(Long storeId, List<CreateStoreAdditionalCommand.StoreImageCommand> imageCommands);

}
