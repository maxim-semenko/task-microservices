package com.example.invoiceservice.service.impl;

import com.example.invoiceservice.dto.CreatedBetResponseDTO;
import com.example.invoiceservice.dto.UserBalanceResponseDTO;
import com.example.invoiceservice.dto.WithdrawMoneyRequestDTO;
import com.example.invoiceservice.entity.BetTypeEnum;
import com.example.invoiceservice.entity.Invoice;
import com.example.invoiceservice.exception.BalanceHistoryException;
import com.example.invoiceservice.exception.ResourseNotFoundException;
import com.example.invoiceservice.feignclient.UserFeignClient;
import com.example.invoiceservice.repository.InvoiceRepository;
import com.example.invoiceservice.service.InvoiceService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * The InvoiceService implementation that realize InvoiceService interface {@link InvoiceService}.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final UserFeignClient userFeignClient;

    /**
     * Method that gets user's balance by their id.
     *
     * @param userId the user's id {@link Long}
     * @return the user's balance {@link Double}
     */
    @Override
    public UserBalanceResponseDTO getUserBalanceByUserId(Long userId) {
        Double balance = invoiceRepository.getBalanceByUserId(userId);
        if (balance == null) {
            throw new ResourseNotFoundException("User's balance was not found!");
        }

        return new UserBalanceResponseDTO(balance);
    }

    /**
     * Method that saves created bet.
     *
     * @param requestDTO the request dto {@link CreatedBetResponseDTO}
     */
    @Override
    public void save(CreatedBetResponseDTO requestDTO) {
        Invoice invoice = new Invoice();
        invoice.setBetUUID(requestDTO.getBetUUID());
        invoice.setPreviousBetUUID(requestDTO.getPreviousBetUUID());
        invoice.setUserId(requestDTO.getUserId());
        invoice.setAmountOfMoney(requestDTO.getAmountOfMoney());
        invoice.setBetTimeStamp(requestDTO.getBetTimeStamp());
        invoice.setType(BetTypeEnum.valueOf(requestDTO.getBetType()));
        invoiceRepository.save(invoice);
    }

    /**
     * Method that withdraws user's money.
     *
     * @param requestDTO the request dto {@link WithdrawMoneyRequestDTO}
     * @return the withdrawal invoice {@link Invoice}
     */
    @Override
    public Invoice withdrawMoney(WithdrawMoneyRequestDTO requestDTO) {
        Long userId = requestDTO.getUserId();
        try {
            userFeignClient.getUserById(userId);
        } catch (Exception e) {
            throw new ResourseNotFoundException("User was not found!");
        }
        Double balance = invoiceRepository.getBalanceByUserId(userId);
        if (balance < 0) {
            throw new BalanceHistoryException("Your balance is lass then zero!");
        } else if (balance < requestDTO.getAmountOfMoney() || requestDTO.getAmountOfMoney() < 0) {
            throw new BalanceHistoryException("Your amountOfMoney is not correct!");
        }

        List<Invoice> invoicesHistory = invoiceRepository.findAllByUserId(userId);
        boolean isHaveConflictBets = false;
        for (int i = 1; i < invoicesHistory.size(); i++) {
            Invoice currentInvoice = invoicesHistory.get(i);
            Invoice previousInvoice = invoicesHistory.get(i - 1);

            if (currentInvoice.getPreviousBetUUID().compareTo(previousInvoice.getBetUUID()) > 0) {
                isHaveConflictBets = true;
                previousInvoice.setType(BetTypeEnum.CONFLICT_BET);
                currentInvoice.setType(BetTypeEnum.CONFLICT_BET);
            }
        }

        if (isHaveConflictBets) {
            throw new BalanceHistoryException("Error in invoice history!");
        }

        Invoice invoice = new Invoice();
        invoice.setUserId(userId);
        invoice.setBetUUID(UUID.randomUUID());
        invoice.setPreviousBetUUID(invoicesHistory.get(invoicesHistory.size() - 1).getBetUUID());
        invoice.setAmountOfMoney(-requestDTO.getAmountOfMoney());
        invoice.setBetTimeStamp(new Date());
        invoice.setType(BetTypeEnum.WITHDRAW_BET);

        return invoiceRepository.save(invoice);
    }

}
