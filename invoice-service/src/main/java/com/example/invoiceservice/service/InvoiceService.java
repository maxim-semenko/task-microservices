package com.example.invoiceservice.service;

import com.example.invoiceservice.controller.dto.UserBalanceResponseDTO;
import com.example.invoiceservice.controller.dto.WithdrawMoneyRequestDTO;
import com.example.invoiceservice.entity.Invoice;

/**
 * The Invoice service interface.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */
public interface InvoiceService {

    UserBalanceResponseDTO getUserBalance(Long userId);

    Invoice withdrawMoney(WithdrawMoneyRequestDTO requestDTO);

}
