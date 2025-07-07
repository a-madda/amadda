package com.seungse.amadda.adapter.in.web.mapper;

import com.seungse.amadda.adapter.in.web.request.CreateStoreMenuRequest;
import com.seungse.amadda.application.port.in.CreateMenuCommand;
import com.seungse.amadda.domain.MenuImageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StoreMenuRequestMapper {

    public CreateMenuCommand mapToCommand(CreateStoreMenuRequest request) {
        List<CreateMenuCommand.MenuImageCommand> detailImageCommands =
            request.getDetailImages().stream().map(multipartFile -> toMenuImageCommand(multipartFile, MenuImageType.DETAIL))
                .toList();

        return CreateMenuCommand.builder()
            .name(request.getName())
            .price(request.getPrice())
            .mainImage(toMenuImageCommand(request.getMainImage(), MenuImageType.MAIN))
            .detailImages(detailImageCommands)
            .description(request.getDescription())
            .available(request.getAvailable())
            .isRepresentative(request.getIsRepresentative())
            .storeId(request.getStoreId())
            .build();
    }

    //MultipartFile -> File로 변환
    private CreateMenuCommand.MenuImageCommand toMenuImageCommand(MultipartFile multipartFile,
                                                                  MenuImageType menuImageType) {
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String prefix = "upload_";
            String suffix = "_" + originalFilename;
            File tempFile = File.createTempFile(prefix, suffix);
            multipartFile.transferTo(tempFile);
            return CreateMenuCommand.MenuImageCommand.builder()
                .file(tempFile)
                .fileType(multipartFile.getContentType())
                .menuImageType(menuImageType)
                .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
