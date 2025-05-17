package com.seungse.amadda.accountservice.infrastructor.advice;

import com.seungse.amadda.accountservice.infrastructor.advice.exceptions.AccountException;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(AccountException.class)
    public ResponseEntity<ErrorResponse> handleAccountException(AccountException e) {
        return ResponseEntity.badRequest()
                .body(ErrorResponse.of(e.getErrorCode(), e.getErrorMessage()));
    }

    @Getter
    private static class ErrorResponse {
        private String errorCode;
        private String errorMessage;

        public static ErrorResponse of(String errorCode, String errorMessage) {
            ErrorResponse response = new ErrorResponse();
            response.errorCode = errorCode;
            response.errorMessage = errorMessage;
            return response;
        }
    }

}
