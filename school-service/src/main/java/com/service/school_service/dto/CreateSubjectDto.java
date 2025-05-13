package com.service.school_service.dto;

import jakarta.validation.constraints.*;

import java.util.Set;
import java.util.UUID;

public record CreateSubjectDto(
        @NotBlank(message = "Name cannot be null")
        String name,

        @NotNull(message = "Teacher ID is required")
        Set<UUID> teacherIds
) {
}
