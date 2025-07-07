package com.seungse.amadda.infrastructure.exceptions.handler;

import com.seungse.amadda.infrastructure.exceptions.StoreException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StoreException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(StoreException ex) {
        log.error("{} 발생: {}", ex.getClass().getName(), ex.getMessage(), ex);

        ExceptionResponse response = new ExceptionResponse(
            ex.getErrorCode(),
            ex.getErrorMessage()
        );

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionResponse> handleException(Exception ex) {
        log.error("{} 발생: {}", ex.getClass().getName(), ex.getMessage(), ex);

        ExceptionResponse response = new ExceptionResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.toString(),
            ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    public record ExceptionResponse(String errorCode, String errorMessage) {

    }

}
