package com.service.school_service.service;

import com.service.school_service.dto.*;
import com.service.school_service.model.School;
import jakarta.transaction.Transactional;

import java.util.List;

public interface SchoolService {
    SchoolDto createSchool(CreateSchoolDto school);
    SchoolDto updateSchool(Long id, UpdateSchoolDto updatedSchool);
    List<SchoolDto> getAllSchools();
    SchoolDto getSchoolById(Long id);
    void deleteSchoolById(Long classId);
    School getEntityById(Long id);
    SchoolClassDto addClassToSchool(Long schoolId, CreateSchoolClassDto dto);
    @Transactional
    void removeClassFromSchool(Long schoolId, Long classId);
}
