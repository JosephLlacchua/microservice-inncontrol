package com.inncontrol.gateway.filters;

import com.inncontrol.gateway.dtos.TokenDto;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class AuthFilter implements GatewayFilter {

    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    private final WebClient.Builder webClientBuilder;

    public AuthFilter(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();

        if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
            logger.warn("Falta el header Authorization");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String authHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            logger.warn("Token mal formado");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);

        // Llamar al endpoint verify-token usando la URL correcta
        logger.info("Validando token: " + token);
        return webClientBuilder.build()
                .post()
                .uri("http://localhost:8094/msvc-iam/api/v1/authentication/verify-token/" + token)
                .retrieve()
                .bodyToMono(TokenDto.class)
                .flatMap(authenticatedUser -> {
                    logger.info("Token válido. Continuando con la solicitud.");
                    return chain.filter(exchange);
                })
                .onErrorResume(e -> {
                    logger.error("Error durante la validación del token", e);
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                });
    }
}