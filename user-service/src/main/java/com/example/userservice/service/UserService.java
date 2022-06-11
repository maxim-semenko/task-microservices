package com.example.userservice.service;

import com.example.userservice.controller.dto.CreateUserRequestDTO;
import com.example.userservice.entity.User;

/**
 * The User service interface.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */
public interface UserService {

    /**
     * Method that finds user by their id.
     *
     * @param id the user's id {@link Long}
     * @return the user {@link User}
     */
    User findById(Long id);

    /**
     * Method that creates a new user.
     *
     * @param requestDTO user data {@link CreateUserRequestDTO}
     * @return the created user {@link User}
     */
    User create(CreateUserRequestDTO requestDTO);
}
