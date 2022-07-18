package com.example.userservice.controller;

import com.example.userservice.dto.CreateUserRequestDTO;
import com.example.userservice.entity.User;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.ws.rs.core.Response;
import java.util.Collections;

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

    @PostMapping("/register")
    public ResponseEntity<Object> createUser() {
        UserRepresentation user = new UserRepresentation();
        user.setEmail("yourniceemail@mail.com");
        user.setUsername("unique_username");
        user.setGroups(Collections.singletonList("user"));
        UsersResource users = keycloak.realm(realm).users();

        Response response = users.create(user);

        if(response.getStatus() != 201) {
            throw new ResponseStatusException(
                    HttpStatus.valueOf(response.getStatus()),
                    response.readEntity(String.class));
        }
        String[] parts = response.getLocation().toString().split("/");
        String userId = parts[parts.length - 1];

        // save user into database
        return ResponseEntity.ok().build();
    }

    /**
     * The UserService for working with user entity {@link User}.
     */
    private final UserService userService;
    private final Keycloak keycloak;

    @Value("${keycloak.realm}")
    private String realm;

    /**
     * Method that finds user by needed their id.
     *
     * @param id the user's id {@link Long}
     * @return the response entity {@link ResponseEntity<User>}
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('role-get-user', 'role-full-access')")
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
    @PreAuthorize("hasAnyRole('role-update-user', 'role-full-access')")
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
