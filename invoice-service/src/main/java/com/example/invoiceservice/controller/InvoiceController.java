package com.example.invoiceservice.controller;

import com.example.invoiceservice.dto.UserBalanceResponseDTO;
import com.example.invoiceservice.dto.WithdrawMoneyRequestDTO;
import com.example.invoiceservice.entity.BetTypeEnum;
import com.example.invoiceservice.entity.Invoice;
import com.example.invoiceservice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * The Invoice REST-controller.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */
@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    /**
     * The InvoiceService for working with invoice entity {@link Invoice}.
     *
     * @param invoiceService the invoice service {@link InvoiceService}
     */
    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    /**
     * Method that returns user's balance.
     *
     * @param userId the user's id {@link Long}
     * @return the response entity {@link ResponseEntity<UserBalanceResponseDTO>}
     */
    @GetMapping("/status/{userId}")
    public ResponseEntity<UserBalanceResponseDTO> getUserBalanceByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(invoiceService.getUserBalanceByUserId(userId), HttpStatus.OK);
    }

    /**
     * Method that withdraws user's money.
     *
     * @return the response entity
     */
    @PostMapping("/withdraw")
    public ResponseEntity<Invoice> withdrawMoney(@RequestBody WithdrawMoneyRequestDTO requestDTO) {
        return new ResponseEntity<>(invoiceService.withdrawMoney(requestDTO), HttpStatus.CREATED);
    }

}
