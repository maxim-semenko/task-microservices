package com.example.invoiceservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserBalanceNotFoundException extends RuntimeException {
    public UserBalanceNotFoundException(String message) {
        super(message);
    }
}
