package com.example.gamblingservice.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

/**
 * The CreatedBetResponseDTO - response with created bet.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */
@Getter
@Setter
public class CreatedBetResponseDTO {
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
