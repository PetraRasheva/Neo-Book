package com.service.school_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TeacherDto(
        //compatible with user-service, no teacherDTO
        @NotNull(message = "ID cannot be null")
        UUID id,
        @NotBlank(message = "First name cannot be null")
        String firstName,
        @NotBlank(message = "Last name cannot be null")
        String lastName,
        @Email
        @NotBlank(message = "Email cannot be null")
        String email
) {
}
