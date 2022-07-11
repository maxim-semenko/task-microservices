package com.example.gamblingservice.config;

import feign.RequestInterceptor;
import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
            // Do what you want to do
            requestTemplate.header("Authorization", "Bearer " + keycloak.tokenManager().getAccessTokenString());
        };
    }
}
