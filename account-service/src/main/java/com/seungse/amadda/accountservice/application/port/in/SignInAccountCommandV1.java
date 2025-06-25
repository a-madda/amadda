package com.seungse.amadda.accountservice.application.port.in;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
public class SignInAccountCommandV1 {

    private String email;
    
    private String password;

}
