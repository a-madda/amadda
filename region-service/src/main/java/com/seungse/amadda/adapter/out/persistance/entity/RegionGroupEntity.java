package com.seungse.amadda.adapter.out.persistance.entity;

import com.seungse.amadda.domain.RegionGroup;
import com.seungse.amadda.generator.IdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.locationtech.jts.geom.Geometry;

@Getter
@Entity
@Builder
@Table(name = "region_groups")
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegionGroupEntity {

    @Id
    @IdGenerator
    private Long id;

    /**
     * 지역 그룹 이름
     */
    private String name;

    /**
     * 지역 그룹의 지리적 경계
     */
    @Column(columnDefinition = "geometry(MultiPolygon, 4326)")
    private Geometry geometry;

    public RegionGroup toDomain() {
        return RegionGroup.builder()
            .regionGroupId(this.id)
            .name(this.name)
            .geometry(this.geometry)
            .build();
    }
}
