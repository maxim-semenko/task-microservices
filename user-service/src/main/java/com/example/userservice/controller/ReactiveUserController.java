package com.example.userservice.controller;

import com.example.userservice.dto.CreateUserRequestDTO;
import com.example.userservice.entity.User;
import com.example.userservice.service.ReactiveUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * The User REST-controller.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class ReactiveUserController {

    /**
     * The UserService for working with user entity {@link User}.
     */
    private final ReactiveUserService reactiveUserService;

    /**
     * Method that finds user by needed their id.
     *
     * @param id the user's id {@link Long}
     * @return the response entity {@link ResponseEntity<User>}
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('role-get-user', 'role-full-access')")
    public Mono<User> findById(@PathVariable Long id) {
        return reactiveUserService.findById(id);
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('role-get-user-list', 'role-full-access')")
    public Flux<User> findAll() {
        return reactiveUserService.findAll();
    }

    /**
     * Method that creates a new user.
     *
     * @param requestDTO user data {@link CreateUserRequestDTO}
     * @return the response entity {@link ResponseEntity<User>}
     */
    @PostMapping("/")
    @PreAuthorize("permitAll()")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<User> create(@RequestBody @Valid CreateUserRequestDTO requestDTO) {
        return reactiveUserService.create(requestDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('role-update-user', 'role-full-access')")
    public Mono<User> update(@RequestBody @Valid User user, @PathVariable Long id) {
        return reactiveUserService.updateById(user, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("permitAll()")
    public Mono<Void> delete(@PathVariable Long id) {
        return reactiveUserService.deleteById(id);
    }

}
