package com.seungse.amadda.adapter.out.persistence.redis;

import com.seungse.amadda.adapter.out.persistence.redis.model.StoreBasicInfo;
import com.seungse.amadda.application.port.out.StoreOutRedisPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StoreOutPortRedisAdapter implements StoreOutRedisPort {

    private final RedisTemplate<String, StoreBasicInfo> redisTemplate;

    @Override
    public String saveBasicInfo(StoreBasicInfo storeBasicInfo) {
        String key = storeBasicInfo.getStoreBasicKey();
        redisTemplate.opsForValue().set(key, storeBasicInfo, Duration.ofMinutes(30));
        return key;
    }

    @Override
    public Optional<StoreBasicInfo> getRedisStore(String key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key));
    }

}
