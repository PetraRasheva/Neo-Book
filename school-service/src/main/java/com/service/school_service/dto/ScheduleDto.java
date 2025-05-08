package com.service.school_service.dto;

import java.util.List;

public record ScheduleDto(
        Long id,
        Long schoolClassId,
        List<SubjectAssignmentDto> subjectAssignments
) {
}
