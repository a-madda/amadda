package com.seungse.amadda.adapter.out.persistance.entity;

import com.seungse.amadda.domain.RegionGroup;
import com.seungse.amadda.generator.IdGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

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
     * 상위 지역 그룹 이름
     */
    private String parentName;

    /**
     * 상위 지역 그룹 코드
     */
    private String parentCode;

    /**
     * 지역 그룹 이름
     */
    private String name;

    /**
     * 지역 그룹 코드
     */
    private String code;

    public RegionGroup toDomain() {
        return RegionGroup.builder()
            .regionGroupId(this.id)
            .parentCode(this.parentCode)
            .parentName(this.parentName)
            .name(this.name)
            .code(this.code)
            .build();
    }
}
