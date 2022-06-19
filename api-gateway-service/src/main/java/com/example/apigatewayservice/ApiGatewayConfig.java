package com.example.apigatewayservice;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ApiGatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user_service_route",
                        route -> route
                                .path("/users/**")
                                .filters(filter -> filter.stripPrefix(1))
                                .uri("lb://user-service"))
                .route("gambling_service_route",
                        route -> route
                                .path("/gambling/**")
                                .filters(filter -> filter.stripPrefix(1))
                                .uri("lb://gambling-service"))
                .route("invoice_service_route",
                        route -> route
                                .path("/invoices/**")
                                .filters(filter -> filter.stripPrefix(1))
                                .uri("lb://invoice-service"))
                .build();
    }
}
