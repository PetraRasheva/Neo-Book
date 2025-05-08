package com.service.school_service.dto;

import java.util.Set;
import java.util.UUID;

public record CreateSubjectDto(
        String name,
        Set<UUID> teacherIds
) {
}
