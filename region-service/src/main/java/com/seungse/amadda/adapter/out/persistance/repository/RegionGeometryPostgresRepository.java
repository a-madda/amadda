package com.seungse.amadda.adapter.out.persistance.repository;

import com.seungse.amadda.adapter.out.persistance.entity.RegionGeometryEntity;
import com.seungse.amadda.domain.RegionGeometry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionGeometryPostgresRepository extends JpaRepository<RegionGeometryEntity, String> {

    /**
     * 지역 코드 목록에 해당하는 지역의 지오메트리를 조회합니다.
     *
     * @param regionCodes 조회할 지역 코드 목록
     * @return  지오메트리 목록
     */
    List<RegionGeometry> findByCodeIn(List<String> regionCodes);

}
