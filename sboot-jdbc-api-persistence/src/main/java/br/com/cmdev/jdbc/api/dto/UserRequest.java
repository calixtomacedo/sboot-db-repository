package br.com.cmdev.jdbc.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank @Size(min = 3, max = 100)
        String name,

        @NotBlank @Email @Size(min = 6, max = 100)
        String email,

        @NotBlank @Size(min = 11, max = 255)
        String password,

        @NotNull
        String role,

        @NotNull
        Boolean isActive
    ) {
}