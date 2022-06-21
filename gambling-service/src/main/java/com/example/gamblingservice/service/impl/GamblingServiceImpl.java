package com.example.gamblingservice.service.impl;

import com.example.gamblingservice.config.MessagingConfig;
import com.example.gamblingservice.dto.CreateBetRequestDTO;
import com.example.gamblingservice.dto.CreatedBetResponseDTO;
import com.example.gamblingservice.exception.ResourseNotFoundException;
import com.example.gamblingservice.feignclient.UserFeignClient;
import com.example.gamblingservice.service.GamblingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * The GamblingService implementation that realize UserService interface {@link GamblingService}.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */
@Service
@Slf4j
public class GamblingServiceImpl implements GamblingService {

    private final RabbitTemplate rabbitTemplate;
    private final UserFeignClient userFeignClient;

    @Autowired
    public GamblingServiceImpl(RabbitTemplate rabbitTemplate,
                               UserFeignClient userFeignClient) {
        this.rabbitTemplate = rabbitTemplate;
        this.userFeignClient = userFeignClient;
    }

    /**
     * Method that creates bet.
     *
     * @param requestDTO the request dto {@link CreateBetRequestDTO}
     * @return the created bet response dto {@link CreatedBetResponseDTO}
     */
    @Override
    public CreatedBetResponseDTO createBet(CreateBetRequestDTO requestDTO) {
        try {
            userFeignClient.getUserById(requestDTO.getUserId());
        } catch (Exception e) {
            throw new ResourseNotFoundException("User was not found!");
        }

        CreatedBetResponseDTO createdBetResponseDTO = new CreatedBetResponseDTO();
        Double betMoney = requestDTO.getMoney();
        createdBetResponseDTO.setBetUUID(UUID.randomUUID());
        createdBetResponseDTO.setPreviousBetUUID(requestDTO.getPreviousBetUUID());
        createdBetResponseDTO.setUserId(requestDTO.getUserId());
        createdBetResponseDTO.setAmountOfMoney(Math.random() > 0.51 ? (betMoney * 2) : -betMoney);
        createdBetResponseDTO.setBetTimeStamp(new Date());
        createdBetResponseDTO.setBetType("GAME_BET");

        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.KEY, createdBetResponseDTO);

        return createdBetResponseDTO;
    }
}
