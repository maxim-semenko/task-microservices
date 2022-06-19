package com.example.invoiceservice.controller;

import com.example.invoiceservice.controller.dto.UserBalanceResponseDTO;
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
    public ResponseEntity<UserBalanceResponseDTO> getUserBalance(@PathVariable Long userId) {
        return new ResponseEntity<>(invoiceService.getUserBalance(userId), HttpStatus.OK);
    }

    /**
     * Method that withdraws user's money.
     *
     * @return the response entity
     */
    @PostMapping("/withdraw")
    public ResponseEntity<Invoice> withdrawMoney() {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
