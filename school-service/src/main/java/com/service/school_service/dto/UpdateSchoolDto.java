package com.service.school_service.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateSchoolDto(
        @NotBlank(message = "Name cannot be null")
        String name,
        @NotBlank(message = "Address cannot be null")
        String address
) {
}
