package com.example.invoiceservice.service;

import com.example.invoiceservice.dto.CreatedBetResponseDTO;
import com.example.invoiceservice.dto.UserBalanceResponseDTO;
import com.example.invoiceservice.dto.WithdrawMoneyRequestDTO;
import com.example.invoiceservice.entity.Invoice;

/**
 * The Invoice service interface.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */
public interface InvoiceService {

    /**
     * Method that gets user's balance by their id.
     *
     * @param userId the user's id {@link Long}
     * @return the user's balance {@link Double}
     */
    UserBalanceResponseDTO getUserBalanceByUserId(Long userId);

    /**
     * Method that withdraws user's money.
     *
     * @param requestDTO the request dto {@link WithdrawMoneyRequestDTO}
     */
    void save(CreatedBetResponseDTO requestDTO);

    /**
     * Method that withdraws user's money.
     *
     * @param requestDTO the request dto {@link WithdrawMoneyRequestDTO}
     * @return the withdrawal invoice {@link Invoice}
     */
    Invoice withdrawMoney(WithdrawMoneyRequestDTO requestDTO);

}
