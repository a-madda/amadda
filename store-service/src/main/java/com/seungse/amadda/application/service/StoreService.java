package com.seungse.amadda.application.service;

import com.seungse.amadda.adapter.out.persistence.redis.model.StoreBasicInfo;
import com.seungse.amadda.application.mapper.StoreCommandMapper;
import com.seungse.amadda.application.port.in.CreateStoreAdditionalCommand;
import com.seungse.amadda.application.port.in.CreateStoreBasicCommand;
import com.seungse.amadda.application.port.in.StoreUseCase;
import com.seungse.amadda.application.port.out.StoreOutPort;
import com.seungse.amadda.application.port.out.StoreOutRedisPort;
import com.seungse.amadda.domain.Store;
import com.seungse.amadda.domain.StoreImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreService implements StoreUseCase {

    private final StoreOutPort storeOutPort;
    private final StoreOutRedisPort storeOutRedisPort;

    @Override
    public String saveBasicInfoToRedis(CreateStoreBasicCommand command) {
        StoreBasicInfo temp = StoreBasicInfo.from(command);
        return storeOutRedisPort.saveBasicInfo(temp);
    }

    @Override
    public Store createStore(CreateStoreBasicCommand basicCommand, CreateStoreAdditionalCommand additionalCommand) {
        return storeOutPort.createStore(StoreCommandMapper.toDomain(basicCommand, additionalCommand));
    }

    @Override
    public Optional<StoreBasicInfo> getRedisStore(String key) {
        return storeOutRedisPort.getRedisStore(key);
    }

    @Override
    public Optional<Store> getStore(Long storeId) {
        return storeOutPort.getStore(storeId);
    }

    @Override
    public void saveStoreImages(Long storeId, List<CreateStoreAdditionalCommand.StoreImageCommand> imageCommands) {
        Store store = storeOutPort.getStore(storeId)
            .orElseThrow(() -> new IllegalArgumentException("Store not found"));

        List<StoreImage> storeImages = imageCommands.stream()
            .map(image -> StoreImage.builder()
                .bucketName(image.getBucketName())
                .filePath(image.getFilePath())
                .originalFileName(image.getOriginalFileName())
                .fileType(image.getFileType())
                .fileSize(image.getFileSize())
                .fileOrder(image.getFileOrder())
                .build())
            .toList();

        store.setStoreImages(storeImages);
        storeOutPort.updateStoreImages(store); // JPA save 또는 merge
    }

}
