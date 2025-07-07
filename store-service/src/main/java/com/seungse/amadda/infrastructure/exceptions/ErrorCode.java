package com.seungse.amadda.infrastructure.exceptions;

import lombok.Getter;

@Getter
public enum ErrorCode {

    STORE_BASIC_KEY_NOT_FOUND("STORE_BASIC_KEY_NOT_FOUND", "상점 기본 정보가 존재하지 않습니다."),

    STORE_NOT_FOUND("STORE_NOT_FOUND", "상점 정보가 존재하지 않습니다.");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
