package com.seungse.amadda.adapter.out.persistence.entity;

import com.seungse.amadda.domain.OperatingHour;
import com.seungse.amadda.domain.OperatingHourType;
import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@Entity
@Builder
@Table(name = "store_operating_hour")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreOperatingHourEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OperatingHourType type;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private LocalTime openTime;

    private LocalTime closeTime;

    private LocalTime breakStartTime;

    private LocalTime breakEndTime;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private StoreEntity store;

    public static StoreOperatingHourEntity from(OperatingHour operatingHour) {
        return StoreOperatingHourEntity.builder()
            .type(operatingHour.getType())
            .dayOfWeek(operatingHour.getDayOfWeek())
            .openTime(operatingHour.getOpenTime())
            .closeTime(operatingHour.getCloseTime())
            .breakStartTime(operatingHour.getBreakStartTime())
            .breakEndTime(operatingHour.getBreakEndTime())
            .build();
    }

    public OperatingHour toDomain() {
        return OperatingHour.builder()
            .type(this.type)
            .dayOfWeek(this.dayOfWeek)
            .openTime(this.openTime)
            .closeTime(this.closeTime)
            .breakStartTime(this.breakStartTime)
            .breakEndTime(this.breakEndTime)
            .build();
    }

}
