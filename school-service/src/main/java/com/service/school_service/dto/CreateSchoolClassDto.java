package com.service.school_service.dto;

import com.service.school_service.enums.GradeLetter;

import java.util.UUID;

public record CreateSchoolClassDto(
        int gradeLevel,
        GradeLetter letter,
        Long schoolId,
        Long specialityId,
        UUID teacherId
) {
}
