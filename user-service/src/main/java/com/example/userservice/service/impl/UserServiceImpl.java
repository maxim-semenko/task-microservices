package com.example.userservice.service.impl;

import com.example.userservice.entity.User;
import com.example.userservice.controller.dto.CreateUserRequestDTO;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User was not found!"));
    }

    @Override
    public User create(CreateUserRequestDTO requestDTO) {
        User user = new User();
        user.setTitle(requestDTO.getTitle());

        return userRepository.save(user);
    }
}
