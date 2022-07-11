package com.example.userservice.controller;

import com.example.userservice.dto.CreateUserRequestDTO;
import com.example.userservice.entity.User;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * The User REST-controller.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    /**
     * The UserService for working with user entity {@link User}.
     */
    private final UserService userService;

    /**
     * Method that finds user by needed their id.
     *
     * @param id the user's id {@link Long}
     * @return the response entity {@link ResponseEntity<User>}
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('role-get-user') and #id == authentication.principal.id or (hasRole('role-full-access'))")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('role-get-user-list', 'role-full-access')")
    public ResponseEntity<User> findAll() {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    /**
     * Method that creates a new user.
     *
     * @param requestDTO user data {@link CreateUserRequestDTO}
     * @return the response entity {@link ResponseEntity<User>}
     */
    @PostMapping("/")
    @PreAuthorize("permitAll()")
    public ResponseEntity<User> create(@RequestBody @Valid CreateUserRequestDTO requestDTO) {
        return new ResponseEntity<>(userService.create(requestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("(hasRole('role-update-user') and #id == authentication.principal.id) " +
            "or (hasRole('role-full-access'))")
    public ResponseEntity<User> update(@PathVariable Long id) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/test/{id}")
    @PreAuthorize("hasAnyRole('role-get-user', 'role-full-access')")
    public String getUser(@PathVariable String id) {
        return "Get user";
    }

    @GetMapping("/test")
    @PreAuthorize("hasAnyRole('role-get-user-list', 'role-full-access')")
    public String getUserList() {
        return "Get user list";
    }

}
