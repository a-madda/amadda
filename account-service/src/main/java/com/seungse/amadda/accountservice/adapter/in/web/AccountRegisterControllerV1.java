package com.seungse.amadda.accountservice.adapter.in.web;

import com.seungse.amadda.accountservice.adapter.in.web.request.RegisterAccountRequest;
import com.seungse.amadda.accountservice.adapter.in.web.response.RegisteredAccountResponse;
import com.seungse.amadda.accountservice.application.port.in.AccountRegisterUseCaseV1;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/v1/accounts")
public class AccountRegisterControllerV1 {

    private final AccountRegisterUseCaseV1 accountRegisterUseCaseV1;

    /**
     * 회원가입
     *
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<RegisteredAccountResponse> saveAccount(@RequestBody @Valid RegisterAccountRequest request) {
        return ResponseEntity.ok(
                accountRegisterUseCaseV1.registerAccount(request.mapToCommand())
                        .map(RegisteredAccountResponse::from)
                        .orElseThrow(() -> new IllegalArgumentException("계정 등록에 실패했습니다."))
        );
    }
}
