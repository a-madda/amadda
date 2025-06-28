package com.seungse.amadda.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;

@Getter
@Entity
@Builder
@Table(name = "store_holiday_day")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreHolidayDayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_holiday_id")
    private StoreHolidayEntity storeHoliday;

}
