package com.service.school_service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ScheduleDto(
        @NotNull(message = "Schedule ID is required")
        Long id,

        @NotNull(message = "School Class ID is required")
        Long schoolClassId,

        @NotEmpty(message = "Subject assignments cannot be empty")
        @Valid // Validates each item in the subjectAssignments list
        List<SubjectAssignmentDto> subjectAssignments
) {
}
