package com.service.school_service.dto;

import java.util.List;

public record ProgramDto(
        Long id,
        String name,
        Long schoolClassId,
        List<SubjectAssignmentDto> subjectAssignments
) {
}
