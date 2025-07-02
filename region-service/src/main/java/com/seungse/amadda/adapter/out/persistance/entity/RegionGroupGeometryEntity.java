package com.seungse.amadda.adapter.out.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.locationtech.jts.geom.Geometry;

@Getter
@Entity
@Builder
@Table(name = "region_group_geometries")
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegionGroupGeometryEntity {

    /**
     * 지역 그룹 아이디
     */
    @Id
    private Long regionGroupId;

    /**
     * 지역 그룹의 지리적 경계
     */
    @Column(columnDefinition = "geometry(MultiPolygon, 4326)")
    private Geometry geometry;

}
