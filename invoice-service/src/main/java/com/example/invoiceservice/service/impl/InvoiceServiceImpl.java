package com.example.invoiceservice.service.impl;

import com.example.invoiceservice.controller.dto.CreatedBetResponseDTO;
import com.example.invoiceservice.controller.dto.UserBalanceResponseDTO;
import com.example.invoiceservice.entity.Invoice;
import com.example.invoiceservice.exception.UserBalanceNotFoundException;
import com.example.invoiceservice.repository.InvoiceRepository;
import com.example.invoiceservice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The InvoiceService implementation that realize InvoiceService interface {@link InvoiceService}.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    /**
     * Initial constructor.
     *
     * @param invoiceRepository the invoice repository {@link InvoiceRepository}
     */
    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    /**
     * Method that gets user's balance by their id.
     *
     * @param userId the user's id {@link Long}
     * @return the user's balance {@link Double}
     */
    @Override
    public UserBalanceResponseDTO getUserBalance(Long userId) {
        Double balance = invoiceRepository.getBalanceByUserId(userId);
        if (balance == null) {
            throw new UserBalanceNotFoundException("User's balance was not found!");
        }

        return new UserBalanceResponseDTO(balance);
    }

    /**
     * Method that saves created bet.
     *
     * @param requestDTO the request dto {@link CreatedBetResponseDTO}
     * @return user's invoice {@link Invoice}
     */
    @Override
    public Invoice save(CreatedBetResponseDTO requestDTO) {
        Invoice invoice = new Invoice();
        invoice.setBetUUID(requestDTO.getBetUUID());
        invoice.setPreviousBetUUID(requestDTO.getPreviousBetUUID());
        invoice.setUserId(requestDTO.getUserId());
        invoice.setAmountOfMoney(requestDTO.getAmountOfMoney());
        invoice.setBetTimeStamp(requestDTO.getBetTimeStamp());

        return invoiceRepository.save(invoice);
    }

    /**
     * Method that withdraws user's money.
     */
    @Override
    public void withdrawMoney() {

    }


}
