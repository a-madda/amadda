package com.seungse.amadda.domain;

import lombok.Builder;
import lombok.Getter;
import org.locationtech.jts.geom.Geometry;

import java.io.Serializable;

@Getter
@Builder
public class RegionGroup implements Serializable {

    private String name;
    private Geometry geometry;

}