package com.service.school_service.dto;

import com.service.school_service.enums.GradeLetter;

import java.util.HashSet;

public record SchoolClassDto(
        Long id,
        String name,
        int gradeLevel,
        GradeLetter letter,
        Long schoolId,
        Long specialityId,
        Long teacherId,
        TeacherDto teacherDto,
        HashSet<StudentDto> students
) {
}
