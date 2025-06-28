package com.seungse.amadda.adapter.in.web;

import com.seungse.amadda.adapter.in.web.mapper.StoreRequestMapper;
import com.seungse.amadda.adapter.in.web.request.CreateStoreAdditionalRequest;
import com.seungse.amadda.adapter.in.web.request.CreateStoreBasicRequest;
import com.seungse.amadda.adapter.in.web.response.CreateStoreResponse;
import com.seungse.amadda.adapter.in.web.response.ReadStoreResponse;
import com.seungse.amadda.adapter.in.web.response.StoreBasicKeyResponse;
import com.seungse.amadda.adapter.out.persistence.redis.model.StoreBasicInfo;
import com.seungse.amadda.application.port.in.CreateStoreAdditionalCommand;
import com.seungse.amadda.application.port.in.CreateStoreBasicCommand;
import com.seungse.amadda.application.port.in.StoreUseCase;
import com.seungse.amadda.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreUseCase storeUseCase;
    private final StoreRequestMapper storeRequestMapper;

    /**
     * 상점 기본 정보 등록 -> Redis 저장
     */
    @PostMapping("/basic")
    public ResponseEntity<StoreBasicKeyResponse> saveBasicInfo(@RequestBody CreateStoreBasicRequest request) {
        String storeBasicKey = storeUseCase.saveBasicInfoToRedis(request.mapToCommand());
        return ResponseEntity.ok(StoreBasicKeyResponse.of(storeBasicKey));
    }

    /**
     * 상점 부가 정보 등록 → Redis + 부가정보
     */
    @PostMapping(value = "/additional", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CreateStoreResponse> create(@RequestPart("request") CreateStoreAdditionalRequest request,
                                                      @RequestPart(value = "storeImages", required = false) List<MultipartFile> storeImages) {
        String storeBasicKey = request.getStoreBasicKey();

        StoreBasicInfo basicInfo = storeUseCase.getRedisStore(storeBasicKey)
            .orElseThrow(() -> new IllegalArgumentException("BasicKey not found: " + storeBasicKey));

        request.setImages(storeImages);
        CreateStoreBasicCommand basicCommand = CreateStoreBasicCommand.mapToCommand(basicInfo);
        CreateStoreAdditionalCommand additionalCommand = storeRequestMapper.mapToCommand(storeBasicKey, request);

        Store savedStore = storeUseCase.createStore(basicCommand, additionalCommand);

        return ResponseEntity.ok(CreateStoreResponse.toResponse(savedStore));
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<ReadStoreResponse> getStore(@PathVariable(value = "storeId") Long storeId) {
        Store store = storeUseCase.getStore(storeId)
            .orElseThrow(() -> new IllegalArgumentException("store not found: " + storeId));
        return ResponseEntity.ok(ReadStoreResponse.from(store));
    }

}
