package com.example.userservice.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * The entity User.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */
@Table(name = "users")
@Data
@Builder
public class User {

    @Id
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String title;

}
