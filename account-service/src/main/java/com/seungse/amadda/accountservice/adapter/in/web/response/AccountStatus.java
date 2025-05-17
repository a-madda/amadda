package com.seungse.amadda.accountservice.adapter.in.web.response;

/**
 * 계정 상태
 */
public enum AccountStatus {
    /**
     * 대기중
     */
    PENDING,
    /**
     * 활성화
     */
    ACTIVE,

    /**
     * 비활성화
     */
    INACTIVE,

    /**
     * 삭제됨
     */
    DELETED,

    /**
     * 정지됨
     */
    SUSPENDED
    ;

    /**
     * 문자열을 받아 계정 상태을 반환.
     *
     * @param status 계정 상태
     * @return 계정 상태
     */
    public static AccountStatus from(String status) {
        return AccountStatus.valueOf(status.toUpperCase());
    }
    
}
