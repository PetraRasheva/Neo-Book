package com.service.school_service.dto;

import com.service.school_service.enums.TimeSlot;

import java.time.DayOfWeek;
import java.util.UUID;

public record SubjectAssignmentDto(
        Long id,
        DayOfWeek dayOfWeek,
        TimeSlot timeSlot,
        UUID teacherId,
        Long subjectId,
        Long programId
) {
}
