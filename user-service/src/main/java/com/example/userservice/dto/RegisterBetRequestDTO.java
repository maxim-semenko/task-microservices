package com.example.userservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
public class RegisterBetRequestDTO {
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
    @NotNull
    private String betType;
}
