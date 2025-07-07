package com.seungse.amadda.adapter.out.persistence.entity;

import com.seungse.amadda.domain.HolidayType;
import com.seungse.amadda.domain.StoreHoliday;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Entity
@Builder
@Table(name = "store_holiday")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreHolidayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean isHoliday;

    @Enumerated(EnumType.STRING)
    private HolidayType holidayType;

    @Setter
    @OneToMany(mappedBy = "storeHoliday", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StoreHolidayDayEntity> holidayDays;

    @Setter
    @OneToOne(optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private StoreEntity store;

    public static StoreHolidayEntity from(StoreHoliday storeHoliday) {
        return StoreHolidayEntity.builder()
            .isHoliday(storeHoliday.isHoliday())
            .holidayType(storeHoliday.getHolidayType())
            .holidayDays(Set.of())
            .build();
    }

    public StoreHoliday toDomain() {
        return StoreHoliday.builder()
            .isHoliday(this.isHoliday)
            .holidayType(this.holidayType)
            .holidayDays(
                this.holidayDays.stream()
                    .map(StoreHolidayDayEntity::getDayOfWeek)
                    .collect(Collectors.toSet())
            )
            .build();
    }

}
