package com.seungse.amadda.application.port.in;

import com.seungse.amadda.domain.MenuImageType;
import com.seungse.amadda.domain.StoreMenu;
import lombok.*;

import java.io.File;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateMenuCommand {

    private String name;

    private int price;

    private MenuImageCommand mainImage;

    private List<MenuImageCommand> detailImages;

    private String description;

    private String available;

    private String isRepresentative;

    private Long storeId;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MenuImageCommand {

        private File file;
        private String fileType;
        private MenuImageType menuImageType;

    }

    public StoreMenu toDomain() {
        return StoreMenu.builder()
            .name(name)
            .price(price)
            .description(description)
            .available("Y")
            .isRepresentative(isRepresentative)
            .storeId(storeId)
            .build();
    }

}
