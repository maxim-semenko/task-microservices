package com.example.invoiceservice.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class WithdrawMoneyRequestDTO {
    @NotNull
    private UUID betUUID;
    @NotNull
    private UUID previousBetUUID;
    @NotNull
    private Long userId;
    @NotNull
    private Double amountOfMoney;
    @NotNull
    private Date betTimeStamp;
}
