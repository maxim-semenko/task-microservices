package com.example.userservice.feignclient;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "data", url = "http://invoice-service")
public interface InvoiceClient {
}
