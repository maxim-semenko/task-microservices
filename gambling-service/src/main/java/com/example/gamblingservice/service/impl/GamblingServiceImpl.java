package com.example.gamblingservice.service.impl;

import com.example.gamblingservice.controller.dto.CreateBetRequestDTO;
import com.example.gamblingservice.controller.dto.CreatedBetResponseDTO;
import com.example.gamblingservice.service.GamblingService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GamblingServiceImpl implements GamblingService {

    @Override
    public CreatedBetResponseDTO createBet(CreateBetRequestDTO requestDTO) {
        CreatedBetResponseDTO createdBetResponseDTO = new CreatedBetResponseDTO();
        createdBetResponseDTO.setBetId(requestDTO.getPreviousBetId() + 1);
        createdBetResponseDTO.setPreviousBetId(requestDTO.getPreviousBetId());
        createdBetResponseDTO.setUserId(requestDTO.getUserId());
        createdBetResponseDTO.setBetTimeStamp(new Date());

        Double betMoney = requestDTO.getMoney();
        if (Math.random() > 0.51) {
            createdBetResponseDTO.setAmountOfMoney(betMoney + (betMoney * 2));
        } else {
            createdBetResponseDTO.setAmountOfMoney(0.0);
        }

        return createdBetResponseDTO;
    }
}
