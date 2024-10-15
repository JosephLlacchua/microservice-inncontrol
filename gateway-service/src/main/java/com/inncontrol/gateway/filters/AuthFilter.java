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
    private final WebClient webClient;

    private static final String AUTH_VALIDATE_URI = "http://msvc-iam/api/v1/authentication/validate";
    private static final String ACCESS_TOKEN_HEADER_NAME = "accessToken";

    public AuthFilter(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            return this.onError(exchange, "Missing Authorization Header");
        }

        final var tokenHeader = exchange
                .getRequest()
                .getHeaders()
                .get(HttpHeaders.AUTHORIZATION).get(0);

        final var chunks = tokenHeader.split(" ");

        if (chunks.length != 2 || !chunks[0].equals("Bearer")) {
            return this.onError(exchange, "Invalid Authorization Header");
        }
        final var token = chunks[1];

        return this.webClient
                .post()
                .uri(AUTH_VALIDATE_URI)
                .header(ACCESS_TOKEN_HEADER_NAME, token)
                .retrieve()
                .bodyToMono(TokenDto.class)
                .flatMap(response -> chain.filter(exchange))
                .onErrorResume(e -> this.onError(exchange, "Token validation failed"));
    }

    private Mono<Void> onError(ServerWebExchange exchange, String error) {
        logger.error(error);
        final var response = exchange.getResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        return response.setComplete();
    }
}