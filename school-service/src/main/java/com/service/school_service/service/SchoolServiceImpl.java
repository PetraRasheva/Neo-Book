package com.service.school_service.service;

import com.service.school_service.dto.*;
import com.service.school_service.exception.SchoolNotFoundException;
import com.service.school_service.mapper.SchoolClassMapper;
import com.service.school_service.mapper.SchoolMapper;
import com.service.school_service.model.School;
import com.service.school_service.model.SchoolClass;
import com.service.school_service.repository.SchoolRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;
    private final SchoolClassMapper schoolClassMapper;
    public SchoolServiceImpl(SchoolRepository schoolRepository, SchoolMapper schoolMapper, SchoolClassMapper schoolClassMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
        this.schoolClassMapper = schoolClassMapper;
    }
    @Override
    public SchoolDto createSchool(CreateSchoolDto schoolDto) {
        School createSchool = this.schoolRepository.save(this.schoolMapper.toEntity(schoolDto));
        return this.schoolMapper.toDto(createSchool);
    }

    @Override
    public SchoolDto updateSchool(Long id, UpdateSchoolDto updatedSchool) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new SchoolNotFoundException(id));

        school.setName(updatedSchool.name());
        school.setAddress(updatedSchool.address());

        return schoolMapper.toDto(schoolRepository.save(school));
    }

    @Override
    public List<SchoolDto> getAllSchools() {
        List<School> schools = this.schoolRepository.findAll();
        return schools.stream().map(schoolMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public SchoolDto getSchoolById(Long id) {
        return this.schoolRepository.findById(id)
                .map(schoolMapper::toDto)
                .orElseThrow(() -> new SchoolNotFoundException(id));
    }

    @Override
    public void deleteSchoolById(Long id) {
        this.schoolRepository.deleteById(id);
    }

    @Override
    public SchoolClassDto addClassToSchool(Long schoolId, CreateSchoolClassDto dto) {
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new SchoolNotFoundException(schoolId));

        SchoolClass schoolClass = schoolClassMapper.toEntity(dto);
        schoolClass.setSchool(school);

        school.getClasses().add(schoolClass);
        schoolRepository.save(school); // This will cascade persist the new class

        return schoolClassMapper.toDto(schoolClass);
    }

    @Transactional
    @Override
    public void removeClassFromSchool(Long schoolId, Long classId) {
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new SchoolNotFoundException(schoolId));

        boolean removed = school.getClasses().removeIf(c -> c.getId().equals(classId));

        if (!removed) {
            throw new IllegalArgumentException("Class ID " + classId + " not found in school " + schoolId);
        }

        schoolRepository.save(school);
    }
}
