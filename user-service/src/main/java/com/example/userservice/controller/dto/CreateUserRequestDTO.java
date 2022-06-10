package com.example.userservice.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateUserRequestDTO {
    @NotBlank(message = "Title must be not blank!")
    @Size(min = 2, max = 50, message = "Title size must be between 2 and 50!")
    private String title;
}
