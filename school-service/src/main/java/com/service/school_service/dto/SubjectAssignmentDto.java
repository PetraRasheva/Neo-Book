package com.service.school_service.dto;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public record SubjectAssignmentDto(
        // Is it a practice to extend the CreateSubj..... but add an id ?
        Long id,
        DayOfWeek dayOfWeek,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Long teacherId,
        Long subjectId,
        Long programId
) {
}
