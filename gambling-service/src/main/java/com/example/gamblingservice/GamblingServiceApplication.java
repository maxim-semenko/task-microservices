package com.example.gamblingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.example.gamblingservice.feignclient")
public class GamblingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamblingServiceApplication.class, args);
    }

}
