package com.seungse.amadda.application.port.out;

import com.seungse.amadda.adapter.out.persistence.redis.model.StoreBasicInfo;

import java.util.Optional;

public interface StoreOutRedisPort {

    String saveBasicInfo(StoreBasicInfo storeBasicInfo);

    Optional<StoreBasicInfo> getRedisStore(String key);

}
