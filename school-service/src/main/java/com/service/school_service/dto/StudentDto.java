package com.service.school_service.dto;

public record StudentDto(
        Long id,
        String firstName,
        String lastName,
        String email
) {
}
