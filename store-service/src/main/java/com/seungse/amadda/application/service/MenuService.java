package com.seungse.amadda.application.service;

import com.seungse.amadda.application.port.in.CreateMenuCommand;
import com.seungse.amadda.application.port.in.StoreMenuUseCase;
import com.seungse.amadda.application.port.out.StoreMenuOutPort;
import com.seungse.amadda.domain.StoreMenu;
import com.seungse.amadda.domain.StoreMenuImage;
import com.seungse.amadda.infrastructure.config.S3Constants;
import com.seungse.amadda.infrastructure.config.S3Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class MenuService implements StoreMenuUseCase {

    private final StoreMenuOutPort menuOutPort;
    private final S3Utils s3Utils;

    @Override
    public List<StoreMenu> getStoreMenus(Long storeId) {
        return menuOutPort.findByStoreId(storeId);
    }

    @Override
    public StoreMenu createMenu(CreateMenuCommand command) {
        StoreMenu storeMenu = menuOutPort.createMenu(command.toDomain());

        StoreMenuImage mainImage = mapToStoreMenuImage(storeMenu, command.getMainImage(), 1);
        List<StoreMenuImage> detailImages = IntStream.range(0, command.getDetailImages().size())
            .mapToObj(i -> mapToStoreMenuImage(storeMenu, command.getDetailImages().get(i), i + 1)).toList();

        List<StoreMenuImage> storeMenuImages = new ArrayList<>();
        storeMenuImages.add(mainImage);
        storeMenuImages.addAll(detailImages);
        storeMenu.setImages(storeMenuImages);

        menuOutPort.updateMenuImages(storeMenu, storeMenuImages);
        return storeMenu;
    }

    private StoreMenuImage mapToStoreMenuImage(StoreMenu storeMenu, CreateMenuCommand.MenuImageCommand command, Integer fileOrder) {
        try {
            String fileUrl = s3Utils.uploadFile(S3Constants.STORE_MENU_DIRECTORY,
                                                String.valueOf(storeMenu.getId()),
                                                command.getFile());
            File file = command.getFile();
            return StoreMenuImage.builder()
                .bucketName(S3Constants.BUCKET_NAME)
                .originalFileName(file.getName())
                .filePath(fileUrl)
                .fileType(command.getFileType())
                .fileSize(file.length())
                .fileOrder(fileOrder)
                .menuImageType(command.getMenuImageType())
                .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteMenu(Long menuId) {

    }

}
