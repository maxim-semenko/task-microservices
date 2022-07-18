package com.example.gamblingservice.feignclient;


import com.example.gamblingservice.config.FeignConfig;
import com.example.gamblingservice.dto.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-service", configuration = FeignConfig.class)
public interface UserFeignClient {

    @GetMapping(path = "/users/{userId}")
    UserResponseDTO getUserById(@PathVariable("userId") Long userId);

}
