package com.seungse.amadda.adapter.out.persistence.entity;

import com.seungse.amadda.adapter.out.persistence.entity.base.BaseImageEntity;
import com.seungse.amadda.domain.MenuImageType;
import com.seungse.amadda.domain.StoreMenuImage;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Entity
@Builder
@Table(name = "store_menu_image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreMenuImageEntity extends BaseImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotNull
    @Enumerated(EnumType.STRING)
    private MenuImageType menuImageType;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_menu_id")
    private StoreMenuEntity storeMenu;

    public StoreMenuImageEntity(StoreMenuImage menuImage) {
        super(
            menuImage.getBucketName(),
            menuImage.getOriginalFileName(),
            menuImage.getFilePath(),
            menuImage.getFileType(),
            menuImage.getFileSize(),
            menuImage.getFileOrder()
        );
    }

    public StoreMenuImage toDomain() {
        return StoreMenuImage.builder()
            .id(id)
            .bucketName(getBucketName())
            .originalFileName(getOriginalFileName())
            .filePath(getFilePath())
            .fileType(getFileType())
            .fileSize(getFileSize())
            .fileOrder(getFileOrder())
            .menuImageType(getMenuImageType())
            .build();
    }

    public static StoreMenuImageEntity from(StoreMenuImage menuImage) {
        StoreMenuImageEntity entity = new StoreMenuImageEntity(menuImage);
        entity.setMenuImageType(menuImage.getMenuImageType());
        return entity;
    }

}
