package com.seungse.amadda.gatewayservice.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class JwtAuthenticationTokenFilter implements GlobalFilter {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return ReactiveSecurityContextHolder.getContext()
            .map(SecurityContext::getAuthentication)
            .flatMap(authentication -> {
                if (authentication.getPrincipal() instanceof Jwt) {
                    Jwt jwt = (Jwt) authentication.getPrincipal();

                    String userId = jwt.getClaimAsString("sub");
                    List<String> roles = jwt.getClaim("realm_access") != null
                        ? ((Map<String, Object>) jwt.getClaim("realm_access")).get("roles") instanceof List
                        ? (List<String>) ((Map<String, Object>) jwt.getClaim("realm_access")).get("roles")
                        : List.of()
                        : List.of();
                    log.error("userId : {} role : {}", userId, roles);
                    ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                        .header("X-User-Id", userId)
                        .header("X-User-Role", String.join(",",roles))
                        .build();

                    return chain.filter(exchange.mutate().request(mutatedRequest).build());
                }
                return chain.filter(exchange);
            }).switchIfEmpty(chain.filter(exchange));
    }


    @NotNull
    public static Mono<Void> getVoidMono(ServerWebExchange exchange,
                                         Map<String, Object> errorMap,
                                         String error,
                                         ObjectMapper objectMapper,
                                         Logger log) {
        try {
            error = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(errorMap);
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException : {}", e.getMessage());
        }
        byte[] bytes = error.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
        return exchange.getResponse().writeWith(Flux.just(buffer));
    }

}