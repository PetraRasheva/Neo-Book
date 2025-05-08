package com.service.school_service.dto;

import com.service.school_service.enums.GradeLetter;

import java.util.HashSet;
import java.util.UUID;

public record SchoolClassDto(
        Long id,
        int gradeLevel,
        GradeLetter letter,
        Long schoolId,
        Long specialityId,
        UUID teacherId,
        TeacherDto teacherDto,
        HashSet<StudentDto> students
) {
}
