package com.example.invoiceservice.service.impl;

import com.example.invoiceservice.controller.dto.WithdrawMoneyRequestDTO;
import com.example.invoiceservice.entity.Invoice;
import com.example.invoiceservice.repository.InvoiceRepository;
import com.example.invoiceservice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Double getUserBalance(Long userId) {
        return invoiceRepository.getBalanceByUserId(userId);
    }

    @Override
    public Invoice withdrawMoney(WithdrawMoneyRequestDTO requestDTO) {
        Invoice invoice = new Invoice();
        invoice.setBetUUID(requestDTO.getBetUUID());
        invoice.setPreviousBetUUID(requestDTO.getPreviousBetUUID());
        invoice.setUserId(requestDTO.getUserId());
        invoice.setAmountOfMoney(requestDTO.getAmountOfMoney());
        invoice.setBetTimeStamp(requestDTO.getBetTimeStamp());

        return invoiceRepository.save(invoice);
    }
}
