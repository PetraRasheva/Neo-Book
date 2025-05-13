package com.service.school_service.dto;

import jakarta.validation.constraints.NotNull;

public record CreateScheduleDto(
        @NotNull(message = "School class ID is required")
        Long schoolClassId
) {
}
