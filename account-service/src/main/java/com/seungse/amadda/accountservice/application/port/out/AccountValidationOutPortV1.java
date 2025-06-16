package com.seungse.amadda.accountservice.application.port.out;

import jakarta.validation.constraints.Email;

public interface AccountValidationOutPortV1 {
    
    boolean isExistAccount(@Email String email);

    boolean isNotExistAccount(String email);
}
