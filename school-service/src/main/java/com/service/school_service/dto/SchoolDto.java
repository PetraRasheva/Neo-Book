package com.service.school_service.dto;

import java.util.List;

public record SchoolDto(
        Long id,
        String name,
        String address,
        List<SchoolClassDto> classes
) {
}
