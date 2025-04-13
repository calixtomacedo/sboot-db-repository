package br.com.cmdev.data.jpa.dto;

public record UserResponse(
        Long userId,
        String name,
        String email,
        String role,
        Boolean isActive,
        String creationDate,
        String changeDate
) {
}
