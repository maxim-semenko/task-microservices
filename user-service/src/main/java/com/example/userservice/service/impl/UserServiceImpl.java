package com.example.userservice.service.impl;

import com.example.userservice.config.MessagingConfig;
import com.example.userservice.dto.CreateUserRequestDTO;
import com.example.userservice.dto.RegisterBetRequestDTO;
import com.example.userservice.entity.User;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * The UserService implementation that realize UserService interface {@link UserService}.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User was not found!"));
    }

    @Override
    public User create(CreateUserRequestDTO requestDTO) {
        User user = new User();
        user.setTitle(requestDTO.getTitle());
        user = userRepository.save(user);

        RegisterBetRequestDTO registerBetRequestDTO = new RegisterBetRequestDTO();
        UUID uuid = UUID.randomUUID();
        registerBetRequestDTO.setUserId(user.getId());
        registerBetRequestDTO.setBetUUID(uuid);
        registerBetRequestDTO.setPreviousBetUUID(uuid);
        registerBetRequestDTO.setAmountOfMoney(20.0);
        registerBetRequestDTO.setBetTimeStamp(new Date());
        registerBetRequestDTO.setBetType("CELEBRITY_BET");

        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.KEY, registerBetRequestDTO);

        return user;
    }
}
