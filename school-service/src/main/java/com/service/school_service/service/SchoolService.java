package com.service.school_service.service;

import com.service.school_service.dto.CreateSchoolDto;
import com.service.school_service.dto.SchoolDto;

import java.util.List;
import java.util.Optional;

public interface SchoolService {
    SchoolDto createSchool(CreateSchoolDto school);

    SchoolDto updateSchool(Long id, SchoolDto updatedSchool);
    List<SchoolDto> getAllSchools();
    Optional<SchoolDto> getSchoolById(Long id); // Optional is meant for a single value that might be absent.
    void deleteSchoolById(Long classId);
}
