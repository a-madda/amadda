package com.seungse.amadda.domain;

import lombok.Builder;
import lombok.Getter;
import org.locationtech.jts.geom.Geometry;

import java.io.Serializable;

@Getter
@Builder
public class RegionGroupGeometry implements Serializable {

    private Long regionGroupId;
    private Geometry geometry;

}