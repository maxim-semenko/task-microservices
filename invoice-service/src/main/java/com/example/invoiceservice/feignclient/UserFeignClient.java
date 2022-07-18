package com.example.invoiceservice.feignclient;


import com.example.invoiceservice.config.FeignConfig;
import com.example.invoiceservice.dto.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-service", configuration = FeignConfig.class)
public interface UserFeignClient {

    @GetMapping(path = "/users/{userId}")
    UserResponseDTO getUserById(@PathVariable("userId") Long userId);

}
