package com.service.school_service.dto;

import com.service.school_service.enums.GradeLetter;
public record CreateSchoolClassDto(
        String name,
        int gradeLevel,
        GradeLetter letter,
        Long schoolId,
        Long specialityId,
        Long teacherId
) {
}
