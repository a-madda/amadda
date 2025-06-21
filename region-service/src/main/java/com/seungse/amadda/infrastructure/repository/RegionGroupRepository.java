package com.seungse.amadda.infrastructure.repository;

public interface RegionGroupRepository<T, E> {

    /**
     * 지역 그룹을 생성합니다.
     *
     * @param regionGroup 지역 그룹 정보
     * @return 생성된 지역 그룹
     */
    T save(T regionGroup);
}
