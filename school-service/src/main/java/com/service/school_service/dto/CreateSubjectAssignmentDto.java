package com.service.school_service.dto;

import com.service.school_service.enums.TimeSlot;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.util.UUID;

public record CreateSubjectAssignmentDto(
        @NotNull(message = "Day of the week is required")
        DayOfWeek dayOfWeek,

        @NotNull(message = "Time slot is required")
        TimeSlot timeSlot,

        @NotNull(message = "Teacher ID is required")
        UUID teacherId,

        @NotNull(message = "Subject ID is required")
        Long subjectId,

        @NotNull(message = "Schedule ID is required")
        Long scheduleId
) {
}
