package com.seungse.amadda.domain;

import lombok.Builder;
import lombok.Getter;
import org.locationtech.jts.geom.Geometry;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Builder
public class RegionGroupGeometry implements Serializable {

    @Serial
    private static final long serialVersionUID = -8723947293847239472L;

    private Long regionGroupId;
    private Geometry geometry;

}