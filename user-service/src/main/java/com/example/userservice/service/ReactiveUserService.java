package com.example.userservice.service;

import com.example.userservice.dto.CreateUserRequestDTO;
import com.example.userservice.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The User service interface.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */
public interface ReactiveUserService {

    /**
     * Method that finds all users.
     *
     * @return the user {@link User}
     */
    Flux<User> findAll();

    /**
     * Method that finds user by their id.
     *
     * @param id the user's id {@link Long}
     * @return the user {@link User}
     */
    Mono<User> findById(Long id);


    /**
     * Method that creates a new user.
     *
     * @param requestDTO user data {@link CreateUserRequestDTO}
     * @return the created user {@link User}
     */
    Mono<User> create(CreateUserRequestDTO requestDTO);

    Mono<User> updateById(User user, Long id);

    Mono<Void> deleteById(Long id);
}
