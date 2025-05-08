package com.service.school_service.dto;

import java.util.UUID;

public record TeacherDto(
        //compatible with user-service, no teacherDTO
        UUID id,
        String firstName,
        String lastName,
        String email
) {
}
