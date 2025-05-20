package com.service.school_service.service;

import com.service.school_service.dto.CreateSchoolClassDto;
import com.service.school_service.dto.ScheduleDto;
import com.service.school_service.dto.SchoolClassDto;
import com.service.school_service.model.SchoolClass;

import java.util.List;
import java.util.UUID;

public interface SchoolClassService {
    SchoolClassDto createSchoolClass(CreateSchoolClassDto schoolClass);
    SchoolClassDto getSchoolClassById(Long classId);
    List<SchoolClassDto> getAllSchoolClasses();
    void deleteSchoolClass(Long id);
    void assignStudent(Long classId, UUID studentId);
    void unassignStudent(Long classId, UUID studentId);
    SchoolClassDto updateSchoolClass(Long id, SchoolClassDto updatedClass); //without touching students
    ScheduleDto getScheduleBySchoolClassId(Long id);
    SchoolClass getEntityById(Long id); // Трябвало да се връща entity for safe reasons....
    List<SchoolClassDto> getClassesBySchoolId(Long schoolId);
}
