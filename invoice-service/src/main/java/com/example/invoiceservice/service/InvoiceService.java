package com.example.invoiceservice.service;

import com.example.invoiceservice.controller.dto.WithdrawMoneyRequestDTO;
import com.example.invoiceservice.entity.Invoice;

public interface InvoiceService {

    Double getUserBalance(Long userId);

    Invoice withdrawMoney(WithdrawMoneyRequestDTO requestDTO);

}
