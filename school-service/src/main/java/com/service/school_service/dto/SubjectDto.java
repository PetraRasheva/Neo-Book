package com.service.school_service.dto;

import java.util.Set;
import java.util.UUID;

public record SubjectDto(
        Long id,
        String name,
        Set<UUID> teacherIds
) {
}
