package com.example.invoiceservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * The WithdrawMoneyRequestDTO.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */
@Getter
@Setter
public class WithdrawMoneyRequestDTO {
    @NotNull
    private Long userId;
    @NotNull
    private Double amountOfMoney;
}
