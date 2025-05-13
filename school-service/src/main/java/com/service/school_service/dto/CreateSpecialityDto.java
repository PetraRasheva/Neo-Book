package com.service.school_service.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateSpecialityDto(
        @NotBlank(message = "Name cannot be null")
        String name
) {
}
