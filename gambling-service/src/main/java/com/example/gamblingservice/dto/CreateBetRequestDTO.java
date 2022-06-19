package com.example.gamblingservice.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * The CreateBetRequestDTO for request to create bet.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */
@Getter
public class CreateBetRequestDTO {
    @NotNull
    private Long userId;
    @NotNull
    private Double money;
    @NotNull
    private UUID previousBetUUID;
}
