package com.example.gamblingservice.service;

import com.example.gamblingservice.dto.CreateBetRequestDTO;
import com.example.gamblingservice.dto.CreatedBetResponseDTO;


/**
 * The Gambling service interface.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */
public interface GamblingService {

    /**
     * Method that creates bet.
     *
     * @param requestDTO the request dto {@link CreateBetRequestDTO}
     * @return the created bet response dto {@link CreatedBetResponseDTO}
     */
    CreatedBetResponseDTO createBet(CreateBetRequestDTO requestDTO);

}
