package com.service.school_service.dto;

import com.service.school_service.enums.GradeLetter;
import com.service.school_service.model.Schedule;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.UUID;

public record SchoolClassDto(
        @NotNull(message = "Class ID is required")
        Long id,

        @Min(value = 1, message = "Grade level must be greater than or equal to 1")
        @Max(value = 12, message = "Grade level must be less than or equal to 12")
        int gradeLevel,

        @NotNull(message = "Grade letter is required")
        GradeLetter letter,

        @NotNull(message = "School ID is required")
        Long schoolId,

        @NotNull(message = "Speciality ID is required")
        Long specialityId,

        @NotNull(message = "Teacher ID is required")
        UUID teacherId,

        @Valid // Ensure the teacherDto is valid if present
        TeacherDto teacherDto,

        @NotEmpty(message = "Students list cannot be empty") // Ensure the students list is not empty
        @Valid // Validates each StudentDto in the list
        HashSet<StudentDto> students,

        Long scheduleId
){
}
