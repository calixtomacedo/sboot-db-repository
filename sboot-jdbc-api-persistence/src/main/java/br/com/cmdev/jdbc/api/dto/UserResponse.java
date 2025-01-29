package br.com.cmdev.jdbc.api.dto;

public record UserResponse(
        Long id,
        String name,
        String email,
        String role,
        Boolean isActive,
        String creationDate,
        String changeDate
    ) {
}