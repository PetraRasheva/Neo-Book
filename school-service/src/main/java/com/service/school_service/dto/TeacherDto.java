package com.service.school_service.dto;

public record TeacherDto(
        Long id,
        String firstName,
        String lastName,
        String email
) {
}
