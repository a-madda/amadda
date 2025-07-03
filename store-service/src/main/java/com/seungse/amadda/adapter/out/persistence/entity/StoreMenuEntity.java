package com.seungse.amadda.adapter.out.persistence.entity;

import com.seungse.amadda.adapter.out.persistence.entity.base.BaseEntity;
import com.seungse.amadda.domain.StoreMenu;
import com.seungse.amadda.domain.StoreMenuImage;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@Table(name = "store_menu")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreMenuEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

    @OneToMany(mappedBy = "storeMenu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StoreMenuImageEntity> menuImages;

    private String description;

    private String available;

    private String isRepresentative;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private StoreEntity store;

    public void addImage(StoreMenuImageEntity image) {
        menuImages.add(image);
        image.setStoreMenu(this);
    }

    public static StoreMenuEntity from(StoreMenu menu, StoreEntity store) {
        return StoreMenuEntity.builder()
            .id(menu.getId())
            .name(menu.getName())
            .price(menu.getPrice())
            .description(menu.getDescription())
            .available(menu.getAvailable())
            .isRepresentative(menu.getIsRepresentative())
            .store(store)
            .menuImages(new ArrayList<>())
            .build();
    }

    public StoreMenu toDomain() {
        List<StoreMenuImage> storeMenuImages = this.menuImages.stream()
            .map(StoreMenuImageEntity::toDomain)
            .toList();

        return StoreMenu.builder()
            .id(this.id)
            .name(this.name)
            .price(this.price)
            .images(storeMenuImages)
            .description(this.description)
            .available(this.available)
            .isRepresentative(this.isRepresentative)
            .storeId(this.store.getId())
            .build();
    }

}
