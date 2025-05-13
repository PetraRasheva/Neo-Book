package com.service.school_service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SchoolDto(
        @NotNull(message = "ID cannot be null")
        Long id,

        @NotBlank(message = "Name cannot be null")
        String name,

        @NotBlank(message = "Address cannot be null")
        String address,

        @NotEmpty(message = "Classes cannot be empty")
        @Valid // Ensures each SchoolClassDto in the list is validated
        List<SchoolClassDto> classes
) {
}
