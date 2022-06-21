package com.example.invoiceservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BalanceHistoryException extends RuntimeException{
    public BalanceHistoryException(String message) {
        super(message);
    }
}
