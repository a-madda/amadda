package com.seungse.amadda.adapter.in.web;

import com.seungse.amadda.adapter.in.web.mapper.StoreMenuRequestMapper;
import com.seungse.amadda.adapter.in.web.request.CreateStoreMenuRequest;
import com.seungse.amadda.application.port.in.StoreMenuUseCase;
import com.seungse.amadda.domain.StoreMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store/menu")
public class StoreMenuController {

    private final StoreMenuUseCase menuUseCase;
    private final StoreMenuRequestMapper storeMenuRequestMapper;

    @GetMapping("/menus/{storeId}")
    public List<StoreMenu> getMenus(@PathVariable(value = "storeId") Long storeId) {
        return menuUseCase.getStoreMenus(storeId);
    }

    @PostMapping
    public ResponseEntity<StoreMenu> create(@ModelAttribute CreateStoreMenuRequest request) {
        return ResponseEntity.ok(menuUseCase.createMenu(storeMenuRequestMapper.mapToCommand(request)));
    }

}
