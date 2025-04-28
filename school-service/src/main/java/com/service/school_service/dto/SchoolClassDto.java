package com.service.school_service.dto;

import java.util.HashSet;

public record SchoolClassDto(
        Long id,
        String name,
        int gradeLevel,
        Long schoolId,
        Long specialityId,
        Long teacherId,
        TeacherDto teacherDto,
        HashSet<StudentDto> students
) {
}
