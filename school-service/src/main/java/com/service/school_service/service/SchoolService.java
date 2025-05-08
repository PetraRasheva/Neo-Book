package com.service.school_service.service;

import com.service.school_service.dto.*;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface SchoolService {
    SchoolDto createSchool(CreateSchoolDto school);
    SchoolDto updateSchool(Long id, UpdateSchoolDto updatedSchool);
    List<SchoolDto> getAllSchools();
    SchoolDto getSchoolById(Long id);
    void deleteSchoolById(Long classId);

    SchoolClassDto addClassToSchool(Long schoolId, CreateSchoolClassDto dto);

    @Transactional
    void removeClassFromSchool(Long schoolId, Long classId);
}
