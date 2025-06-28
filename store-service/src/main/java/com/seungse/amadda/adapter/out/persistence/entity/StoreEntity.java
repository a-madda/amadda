package com.seungse.amadda.adapter.out.persistence.entity;

import com.seungse.amadda.adapter.out.persistence.entity.base.BaseEntity;
import com.seungse.amadda.domain.Category;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Entity
@Builder
@Table(name = "store")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 사업자 등록번호
     */
    private String businessNumber;

    private String storeName;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Setter
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StoreImageEntity> storeImages;

    private String description;

    private String phoneNumber;

    private String address;

    @Setter
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StoreAmenityEntity> amenities;

    @Setter
    @OneToOne(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true, optional = true)
    private StoreHolidayEntity storeHoliday;

    @Setter
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StoreOperatingHourEntity> operatingHours;

    //    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    //    private List<StoreMenuEntity> menus;

}
