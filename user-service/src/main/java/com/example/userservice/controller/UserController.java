package com.example.userservice.controller;

import com.example.userservice.controller.dto.CreateUserRequestDTO;
import com.example.userservice.entity.User;
import com.example.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * The User REST-controller.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    /**
     * The UserService for working with user entity {@link User}.
     *
     * @param userService the user service {@link UserService}
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Method that finds user by needed their id.
     *
     * @param id the user's id {@link Long}
     * @return the response entity {@link ResponseEntity<User>}
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    /**
     * Method that creates a new user.
     *
     * @param requestDTO user data {@link CreateUserRequestDTO}
     * @return the response entity {@link ResponseEntity<User>}
     */
    @PostMapping("/")
    public ResponseEntity<User> create(@RequestBody @Valid CreateUserRequestDTO requestDTO) {
        return new ResponseEntity<>(userService.create(requestDTO), HttpStatus.CREATED);
    }

}
