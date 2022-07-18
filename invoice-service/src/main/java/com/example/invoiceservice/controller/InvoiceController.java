package com.example.invoiceservice.controller;

import com.example.invoiceservice.dto.UserBalanceResponseDTO;
import com.example.invoiceservice.dto.WithdrawMoneyRequestDTO;
import com.example.invoiceservice.entity.Invoice;
import com.example.invoiceservice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Invoice REST-controller.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */
@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    /**
     * Method that returns user's balance.
     *
     * @param userId the user's id {@link Long}
     * @return the response entity {@link ResponseEntity<UserBalanceResponseDTO>}
     */
    @GetMapping("/status/{userId}")
    @PreAuthorize("hasAnyRole('role-get-balance', 'role-full-access')")
    public ResponseEntity<UserBalanceResponseDTO> getUserBalanceByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(invoiceService.getUserBalanceByUserId(userId), HttpStatus.OK);
    }

    /**
     * Method that withdraws user's money.
     *
     * @return the response entity
     */
    @PostMapping("/withdraw")
    @PreAuthorize("hasAnyRole('role-withdraw')")
    public ResponseEntity<Invoice> withdrawMoney(@RequestBody WithdrawMoneyRequestDTO requestDTO) {
        return new ResponseEntity<>(invoiceService.withdrawMoney(requestDTO), HttpStatus.CREATED);
    }

}
