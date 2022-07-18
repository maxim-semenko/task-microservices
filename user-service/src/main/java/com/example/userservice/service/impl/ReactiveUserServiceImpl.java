package com.example.userservice.service.impl;

import com.example.userservice.config.MessagingConfig;
import com.example.userservice.dto.CreateUserRequestDTO;
import com.example.userservice.dto.RegisterBetRequestDTO;
import com.example.userservice.entity.User;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.repository.ReactiveUserRepository;
import com.example.userservice.service.ReactiveUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.UUID;

/**
 * The UserService implementation that realize UserService interface {@link ReactiveUserService}.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */
@Service
@RequiredArgsConstructor
public class ReactiveUserServiceImpl implements ReactiveUserService {

    private final RabbitTemplate rabbitTemplate;
    private final ReactiveUserRepository reactiveUserRepository;

    @Override
    public Flux<User> findAll() {
        return reactiveUserRepository.findAll();
    }

    @Override
    public Mono<User> findById(Long id) {
        return reactiveUserRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new UserNotFoundException("User was not found!")));
    }

    @Override
    public Mono<User> create(CreateUserRequestDTO requestDTO) {
        User user = User.builder().title(requestDTO.getTitle()).build();
        Mono<User> savedUser = reactiveUserRepository.save(user);

        rabbitTemplate.convertAndSend(
                MessagingConfig.EXCHANGE,
                MessagingConfig.KEY,
                createRegisterBet(user.getId(), UUID.randomUUID())
        );

        return savedUser;
    }

    @Override
    public Mono<User> updateById(User user, Long id) {
        return reactiveUserRepository.save(user);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return reactiveUserRepository.deleteById(id);
    }

    private RegisterBetRequestDTO createRegisterBet(Long userId, UUID uuid) {
        return RegisterBetRequestDTO.builder()
                .userId(userId)
                .betUUID(uuid)
                .previousBetUUID(uuid)
                .amountOfMoney(20.0)
                .betTimeStamp(new Date())
                .betType("CELEBRITY_BET")
                .build();
    }
}
