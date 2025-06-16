package com.seungse.amadda.accountservice.adapter.in.web.request;

import com.seungse.amadda.accountservice.application.port.in.AccountRegisterCommandV1;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class RegisterAccountRequest {

    /**
     * 이메일
     */
    private String email;
    /**
     * 전화번호
     */
    private String phone;
    /**
     * 비밀번호
     */
    private String password;
    /**
     * 비밀번호 확인
     */
    private String passwordConfirm;

    public AccountRegisterCommandV1 mapToCommand() {
        return AccountRegisterCommandV1.builder()
                .email(email)
                .phone(phone)
                .password(password)
                .passwordConfirm(passwordConfirm)
                .build();
    }

}
