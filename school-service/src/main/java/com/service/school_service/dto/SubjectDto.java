package com.service.school_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;
import java.util.UUID;

public record SubjectDto(
        @NotNull(message = "ID cannot be null")
        Long id,
        @NotBlank(message = "Name cannot be null")
        String name,
        @NotNull(message = "Teacher ID cannot be null")
        Set<UUID> teacherIds
) {
}
