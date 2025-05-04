package com.service.school_service.service;

import com.service.school_service.dto.CreateSchoolClassDto;
import com.service.school_service.dto.SchoolClassDto;

import java.util.List;

public interface SchoolClassService {
    SchoolClassDto createSchoolClass(CreateSchoolClassDto schoolClass);
    SchoolClassDto getSchoolClass(Long classId);
    List<SchoolClassDto> getAllSchoolClasses();
    void deleteSchoolClass(Long id);
    SchoolClassDto updateSchoolClass(Long id, SchoolClassDto updatedClass);
}
