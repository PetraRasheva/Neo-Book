package com.service.school_service.dto;

import java.util.UUID;

public record StudentDto(
        UUID id,
        String firstName,
        String lastName,
        String email
) {
}
