package com.seungse.amadda.adapter.out.persistence.entity;

import com.seungse.amadda.adapter.out.persistence.entity.base.BaseImageEntity;
import com.seungse.amadda.domain.StoreImage;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "store_image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreImageEntity extends BaseImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private StoreEntity store;

    public StoreImageEntity(StoreImage storeImage) {
        super(
            storeImage.getBucketName(),
            storeImage.getOriginalFileName(),
            storeImage.getFilePath(),
            storeImage.getFileType(),
            storeImage.getFileSize(),
            storeImage.getFileOrder()
        );
    }

    public void delete() {
        this.setIsDeleted(true);
    }

    public long getStoreId() {
        return store.getId();
    }

    public static StoreImageEntity from(StoreImage storeImage) {
        return new StoreImageEntity(storeImage);
    }

    public StoreImage toDomain() {
        return StoreImage.builder()
            .bucketName(this.getBucketName())
            .originalFileName(this.getOriginalFileName())
            .filePath(this.getFilePath())
            .fileType(this.getFileType())
            .fileSize(this.getFileSize())
            .fileOrder(this.getFileOrder())
            .build();
    }

}
