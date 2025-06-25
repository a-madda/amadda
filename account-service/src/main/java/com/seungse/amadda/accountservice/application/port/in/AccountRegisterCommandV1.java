package com.seungse.amadda.accountservice.application.port.in;

import com.seungse.amadda.accountservice.domain.Account;
import com.seungse.amadda.accountservice.infrastructor.SelfValidating;
import com.seungse.amadda.accountservice.infrastructor.advice.exceptions.NotMatchedPasswordException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 계정 등록 커맨드
 */
@Getter
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
public class AccountRegisterCommandV1 extends SelfValidating<AccountRegisterCommandV1> {

    @Email
    private String email;

    @NotNull
    private String phone;

    @NotNull
    private String password;

    @NotNull
    private String passwordConfirm;

    /**
     * 계정 등록 커맨드 생성
     *
     * @param email           이메일
     * @param password        비밀번호
     * @param passwordConfirm 비밀번호 확인
     */
    @Builder
    public static AccountRegisterCommandV1 create(String email, String phone, String password, String passwordConfirm) {
        return new AccountRegisterCommandV1(email, phone, password, passwordConfirm);
    }

    public Account mapToDomain() {
        return Account.builder()
                .email(this.email)
                .phone(this.phone)
                .password(this.password)
                .build();
    }

    public static class AccountRegisterCommandV1Builder {

        public AccountRegisterCommandV1 build() {
            AccountRegisterCommandV1 command = create(email, phone, password, passwordConfirm);
            command.validateSelf();
            if (!password.equals(passwordConfirm)) {
                throw new NotMatchedPasswordException();
            }
            return command;
        }
    }

}
