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
import com.seungse.amadda.infrastructure.exceptions.StoreBasicKeyNotFoundException;
import com.seungse.amadda.infrastructure.exceptions.StoreNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store")
public class StoreController {

    private final StoreUseCase storeUseCase;
    private final StoreRequestMapper storeRequestMapper;

    @PostMapping("/basic")
    public ResponseEntity<StoreBasicKeyResponse> saveBasicInfo(@RequestBody CreateStoreBasicRequest request) {
        String storeBasicKey = storeUseCase.saveBasicInfoToRedis(request.mapToCommand());
        StoreBasicInfo info = storeUseCase.getRedisStore(storeBasicKey)
            .orElseThrow(StoreBasicKeyNotFoundException::new);
        return ResponseEntity.ok(StoreBasicKeyResponse.from(info));
    }

    @PostMapping(value = "/additional", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CreateStoreResponse> create(@RequestPart("request") CreateStoreAdditionalRequest request,
                                                      @RequestPart(value = "storeImages", required = false) List<MultipartFile> storeImages) {
        String storeBasicKey = request.getStoreBasicKey();

        StoreBasicInfo basicInfo = storeUseCase.getRedisStore(storeBasicKey)
            .orElseThrow(StoreBasicKeyNotFoundException::new);

        request.setImages(storeImages);
        CreateStoreBasicCommand basicCommand = CreateStoreBasicCommand.mapToCommand(basicInfo);
        CreateStoreAdditionalCommand additionalCommand = storeRequestMapper.mapToCommand(storeBasicKey, request);

        Store savedStore = storeUseCase.createStore(basicCommand, additionalCommand);

        return ResponseEntity.ok(CreateStoreResponse.toResponse(savedStore));
    }

    @GetMapping("/basic/{storeBasicKey}")
    public ResponseEntity<StoreBasicKeyResponse> getBasicInfo(@PathVariable("storeBasicKey") String storeBasicKey) {
        StoreBasicInfo info = storeUseCase.getRedisStore(storeBasicKey)
            .orElseThrow(StoreBasicKeyNotFoundException::new);

        return ResponseEntity.ok(StoreBasicKeyResponse.from(info));
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<ReadStoreResponse> getStore(@PathVariable(value = "storeId") Long storeId) {
        Store store = storeUseCase.getStore(storeId)
            .orElseThrow(StoreNotFoundException::new);
        return ResponseEntity.ok(ReadStoreResponse.from(store));
    }

}
