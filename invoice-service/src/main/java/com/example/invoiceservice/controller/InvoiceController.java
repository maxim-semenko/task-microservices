package com.example.invoiceservice.controller;

import com.example.invoiceservice.controller.dto.WithdrawMoneyRequestDTO;
import com.example.invoiceservice.entity.Invoice;
import com.example.invoiceservice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/status/{userId}")
    public ResponseEntity<Double> getUserBalance(@PathVariable Long userId) {
        return new ResponseEntity<>(invoiceService.getUserBalance(userId), HttpStatus.OK);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Invoice> withdrawMoney(@RequestBody @Valid WithdrawMoneyRequestDTO requestDTO) {
        return new ResponseEntity<>(invoiceService.withdrawMoney(requestDTO), HttpStatus.OK);
    }
}
