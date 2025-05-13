package com.service.school_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SpecialityDto(
        @NotNull(message = "ID cannot be null")
        Long id,
        @NotBlank(message = "Name cannot be null")
        String name
) {
}
