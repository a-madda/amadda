package com.seungse.amadda.adapter.out.persistance.entity;

import com.seungse.amadda.generator.IdGenerator;
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
    private Geometry geometry;

}
