package com.service.school_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SchoolDto(
        @NotNull(message = "ID cannot be null")
        Long id,

        @NotBlank(message = "Name cannot be null")
        String name,

        @NotBlank(message = "Address cannot be null")
        String address

//        @NotEmpty(message = "Classes cannot be empty")
//        @Valid // Ensures each SchoolClassDto in the list is validated
//        List<SchoolClassDto> classes
) {
}
