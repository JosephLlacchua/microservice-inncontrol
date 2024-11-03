package com.inncontrol.gateway.beans;

import com.inncontrol.gateway.filters.AuthFilter;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@AllArgsConstructor
public class GatewayBeans {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public AuthFilter authFilter(WebClient.Builder webClientBuilder) {
        return new AuthFilter(webClientBuilder);
    }

    @Bean
    @Profile(value = "oauth2")
    public RouteLocator routeLocatorOauth2(RouteLocatorBuilder builder, AuthFilter authFilter) {
        return builder
                .routes()
                .route(route -> route
                        .path("/msvc-accommodation/api/v1/room/**")
                        .filters(filter -> filter.filter(authFilter))
                        .uri("lb://msvc-accommodation")
                )
                .route(route -> route
                        .path("/msvc-inventory/api/v1/inventory/**")
                        .filters(filter -> filter.filter(authFilter))
                        .uri("lb://msvc-inventory")
                )
                .route(route -> route
                        .path("/msvc-messages/api/v1/messages/**")
                        .filters(filter -> filter.filter(authFilter))
                        .uri("lb://msvc-messages")
                )
                .route(route -> route
                        .path("/msvc-tasks/api/v1/tasks/**")
                        .filters(filter -> filter.filter(authFilter))
                        .uri("lb://msvc-tasks")
                )
                .route(route ->route
                        .path("/msvc-employees/api/v1/employees/**")
                        .filters(filter -> filter.filter(authFilter))
                        .uri("lb://msvc-employees")
                )
                .route(route -> route
                        .path("/msvc-iam/api/v1/users/**")
                        .filters(filter -> filter.filter(authFilter))
                        .uri("lb://msvc-iam")
                )
                .route(route -> route
                        .path("/msvc-iam/api/v1/roles/**")
                        .filters(filter -> filter.filter(authFilter))
                        .uri("lb://msvc-iam")
                )
                .route(route -> route
                        .path("/msvc-profiles/api/v1/profiles/**")
                        .filters(filter -> filter.filter(authFilter))
                        .uri("lb://msvc-profiles")
                )
                .route(route -> route
                        .path("/msvc-iam/api/v1/authentication/**")
                        .uri("lb://msvc-iam")
                )
                .build();
    }
}