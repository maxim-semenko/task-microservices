package com.example.gamblingservice.service;

import com.example.gamblingservice.controller.dto.CreateBetRequestDTO;
import com.example.gamblingservice.controller.dto.CreatedBetResponseDTO;


public interface GamblingService {

    CreatedBetResponseDTO createBet(CreateBetRequestDTO requestDTO);

}
