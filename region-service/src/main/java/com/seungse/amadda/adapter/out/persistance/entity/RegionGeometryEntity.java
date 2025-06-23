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
@Table(name = "region_geometries")
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegionGeometryEntity {

    @Id
    private String code;

    @Column(columnDefinition = "geometry(MultiPolygon, 4326)")
    private Geometry geometry;

}
