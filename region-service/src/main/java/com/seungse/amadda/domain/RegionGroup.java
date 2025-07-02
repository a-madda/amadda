package com.seungse.amadda.domain;

import lombok.Builder;
import lombok.Getter;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Builder
public class RegionGroup implements Serializable {

    @Serial
    private static final long serialVersionUID = -3914720384739203842L;

    private Long regionGroupId;
    private String parentName;
    private String parentCode;
    private String name;
    private String code;

}