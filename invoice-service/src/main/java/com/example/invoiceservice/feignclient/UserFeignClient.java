package com.example.invoiceservice.feignclient;


import com.example.invoiceservice.dto.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface UserFeignClient {

    @GetMapping(path = "/api/v1/users/{userId}")
    UserResponseDTO getUserById(@PathVariable("userId") Long userId);

}
