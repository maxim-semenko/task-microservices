package com.example.gamblingservice.config;

import feign.RequestInterceptor;
import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class FeignConfig {

    Keycloak keycloak;

    @Autowired
    public FeignConfig(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    @Bean
    public RequestInterceptor requestTokenBearerInterceptor() {
        return requestTemplate -> {
            requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer " + keycloak.tokenManager().getAccessTokenString());
        };
    }
}
