package com.seungse.amadda.infrastructure.repository;

import com.seungse.amadda.domain.RegionGeometry;

import java.util.List;

public interface RegionGeometryRepository<T, E> {

    /**
     * 지역 코드 목록에 해당하는 지역의 지오메트리를 조회합니다.
     *
     * @param regionCodes 조회할 지역 코드 목록
     * @return  지오메트리 목록
     */
    List<RegionGeometry> findByCodeIn(List<String> regionCodes);

}
