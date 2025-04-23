package com.service.school_service.dto;

public record CreateSchoolClassDto(
        String name,
        int gradeLevel,
        Long teacherId
) {
}
