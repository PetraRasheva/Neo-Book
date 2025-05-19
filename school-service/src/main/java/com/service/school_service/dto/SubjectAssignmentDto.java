package com.service.school_service.dto;

import com.service.school_service.enums.TimeSlot;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.util.UUID;

public record SubjectAssignmentDto(
        @NotNull(message = "ID cannot be null")
        Long id,

        @NotNull(message = "Day of week cannot be null")
        DayOfWeek dayOfWeek,

        @NotNull(message = "Time slot cannot be null")
        TimeSlot timeSlot,

        @NotNull(message = "Teacher ID cannot be null")
        UUID teacherId,

        @NotNull(message = "Subject ID cannot be null")
        Long subjectId,

        @NotNull(message = "Program ID cannot be null")
        Long scheduleId
) {
}
