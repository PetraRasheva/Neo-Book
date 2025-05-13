package com.service.school_service.dto;

import com.service.school_service.enums.GradeLetter;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateSchoolClassDto(
        @NotNull(message = "Grade level is required")
        @Min(value = 1, message = "Grade level must be at least 1")
        @Max(value = 12, message = "Grade level must not exceed 12")
        int gradeLevel,
        @NotNull(message = "Grade letter is required")
        GradeLetter letter,

        @NotNull(message = "School ID is required")
        Long schoolId,

        @NotNull(message = "Speciality ID is required")
        Long specialityId,

        @NotNull(message = "Teacher ID is required")
        UUID teacherId
) {
}
