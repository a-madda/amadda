package com.seungse.amadda.accountservice.infrastructor.advice.exceptions;

import lombok.Getter;

@Getter
public enum ErrorCode {

    /**
     * 비밀번호 불일치
     */
    NOT_MATCHED_PASSWORD("NOT_MATCHED_PASSWORD", "비밀번호가 일치하지 않습니다."),

    /**
     * 비밀번호 길이 오류
     */
    PASSWORD_LENGTH_ERROR("PASSWORD_LENGTH_ERROR", "비밀번호는 8자 이상 20자 이하로 설정해야 합니다."),

    /**
     * 비밀번호 특수문자 오류
     */
    PASSWORD_SPECIAL_CHARACTER_ERROR("PASSWORD_SPECIAL_CHARACTER_ERROR", "비밀번호는 특수문자를 포함해야 합니다."),

    /**
     * 비밀번호 대문자 오류
     */
    PASSWORD_UPPERCASE_ERROR("PASSWORD_UPPERCASE_ERROR", "비밀번호는 대문자를 포함해야 합니다."),

    /**
     * 비밀번호 소문자 오류
     */
    PASSWORD_LOWERCASE_ERROR("PASSWORD_LOWERCASE_ERROR", "비밀번호는 소문자를 포함해야 합니다."),

    /**
     * 비밀번호 숫자 오류
     */
    PASSWORD_NUMBER_ERROR("PASSWORD_NUMBER_ERROR", "비밀번호는 숫자를 포함해야 합니다.");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
