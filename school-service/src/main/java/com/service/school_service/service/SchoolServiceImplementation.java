package com.service.school_service.service;

import com.service.school_service.dto.CreateSchoolDto;
import com.service.school_service.dto.SchoolClassDto;
import com.service.school_service.dto.SchoolDto;
import com.service.school_service.mapper.SchoolClassMapper;
import com.service.school_service.mapper.SchoolMapper;
import com.service.school_service.model.School;
import com.service.school_service.model.SchoolClass;
import com.service.school_service.repository.SchoolRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SchoolServiceImplementation implements SchoolService {
    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;
    private final SchoolClassMapper schoolClassMapper;
    public SchoolServiceImplementation(SchoolRepository schoolRepository, SchoolMapper schoolMapper, SchoolClassMapper schoolClassMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
        this.schoolClassMapper = schoolClassMapper;
    }
    @Override
    public SchoolDto createSchool(CreateSchoolDto schoolDto) {
        School createSchool = this.schoolRepository.save(this.schoolMapper.toEntity(schoolDto));
        return this.schoolMapper.toDto(createSchool);
    }

    /**
     * Updates a {@link School} identified by the given ID with the provided data.
     *
     * <p>This method performs the following operations:</p>
     * <ul>
     *   <li>Loads the existing school from the database (throws if not found).</li>
     *   <li>Updates basic school fields such as name and address.</li>
     *   <li>Synchronizes the list of school classes:
     *       <ul>
     *         <li>Removes classes that are not present in the updated DTO list.</li>
     *         <li>Adds new classes with no ID.</li>
     *         <li>Updates existing classes based on matching IDs.</li>
     *       </ul>
     *   </li>
     *   <li>Saves the updated school entity to the database.</li>
     * </ul>
     *
     * @param id the ID of the school to update
     * @param updatedSchool the DTO containing updated school data including classes
     * @return the updated {@link SchoolDto}
     * @throws RuntimeException if the school or a school class to update is not found
     */
    @Override
    public SchoolDto updateSchool(Long id, SchoolDto updatedSchool) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("School not found"));

        school.setName(updatedSchool.name());
        school.setAddress(updatedSchool.address());

        List<SchoolClass> existingClasses = school.getClasses();
        List<SchoolClassDto> updatedClassDtos = updatedSchool.classes();

        Map<Long, SchoolClassDto> updatedClassesById = updatedClassDtos.stream()
                .filter(c -> c.id() != null)
                .collect(Collectors.toMap(SchoolClassDto::id, Function.identity()));

        // Remove classes that no longer exist in updated list
        existingClasses.removeIf(schoolClass ->
                !updatedClassesById.containsKey(schoolClass.getId())
        );

        // Update existing classes and add new ones
        for (SchoolClassDto dto : updatedClassDtos) {
            if (dto.id() == null) {
                // New class - map and add
                SchoolClass newClass = schoolClassMapper.toEntity(dto);
                newClass.setSchool(school);
                existingClasses.add(newClass);
            } else {
                // Existing class - update fields
                SchoolClass existingClass = existingClasses.stream()
                        .filter(c -> c.getId().equals(dto.id()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Class not found: " + dto.id()));

                existingClass.setName(dto.name());
                existingClass.setGradeLevel(dto.gradeLevel());
                // You can update other mutable fields here
            }
        }

        School saved  = this.schoolRepository.save(school);
        return this.schoolMapper.toDto(saved);
    }

    @Override
    public List<SchoolDto> getAllSchools() {
        List<School> schools = this.schoolRepository.findAll();
        return schools.stream().map(schoolMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<SchoolDto> getSchoolById(Long id) {
        return this.schoolRepository.findById(id).map(schoolMapper::toDto);
    }

    @Override
    public void deleteSchoolById(Long id) {
        this.schoolRepository.deleteById(id);
    }
}
