package com.example.gamblingservice.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class CreatedBetResponseDTO {
    @NotNull
    private Long betId;
    @NotNull
    private Long previousBetId;
    @NotNull
    private Long userId;
    @NotNull
    private Double amountOfMoney;
    @NotNull
    private Date betTimeStamp;
}
