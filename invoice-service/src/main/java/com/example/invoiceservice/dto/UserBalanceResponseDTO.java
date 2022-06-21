package com.example.invoiceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The UserBalanceResponseDTO.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserBalanceResponseDTO {
    private Double balance;
}
