package com.example.userservice.service;

import com.example.userservice.controller.dto.CreateUserRequestDTO;
import com.example.userservice.entity.User;

public interface UserService {

    User findById(Long id);

    User create(CreateUserRequestDTO requestDTO);
}
