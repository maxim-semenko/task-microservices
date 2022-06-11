package com.example.gamblingservice.controller.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
public class CreateBetRequestDTO {
    @NotNull
    private Long userId;
    @NotNull
    private Double money;
    @NotNull
    private UUID previousBetUUID;
}
