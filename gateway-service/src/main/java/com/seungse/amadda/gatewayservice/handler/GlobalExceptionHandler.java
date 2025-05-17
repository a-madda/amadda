package com.seungse.amadda.gatewayservice.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

import static com.seungse.amadda.gatewayservice.jwt.JwtAuthenticationTokenFilter.getVoidMono;

@Slf4j
@Order(-1)
@RequiredArgsConstructor
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

    private final ObjectMapper objectMapper;

    @NotNull
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, @NotNull Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        if (response.isCommitted()) {
            log.error("isCommitted :: {}", response.isCommitted());
            return Mono.error(ex);
        }
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        if (ex instanceof ResponseStatusException responsestatusexception) {
            response.setStatusCode(responsestatusexception.getStatusCode());
        }

        Map<String, Object> errorMap = new HashMap<>();
        StringTokenizer stringTokenizer = new StringTokenizer(Objects.requireNonNull(response.getStatusCode()).toString(), " ");
        if (stringTokenizer.hasMoreTokens()) {
            errorMap.put("success", false);
            errorMap.put("status", Integer.parseInt(stringTokenizer.nextToken()));
            errorMap.put("response", new HashMap<>());
            errorMap.put("message", ex.getMessage());
        }

        String error = "Gateway Error";

        return getVoidMono(exchange, errorMap, error, objectMapper, log);
    }

}