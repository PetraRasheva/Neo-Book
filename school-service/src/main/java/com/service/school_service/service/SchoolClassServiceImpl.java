package com.service.school_service.service;

import com.service.school_service.client.StudentClient;
import com.service.school_service.client.TeacherClient;
import com.service.school_service.dto.CreateSchoolClassDto;
import com.service.school_service.dto.SchoolClassDto;
import com.service.school_service.dto.StudentDto;
import com.service.school_service.dto.TeacherDto;
import com.service.school_service.exception.SchoolClassNotFoundException;
import com.service.school_service.mapper.SchoolClassMapper;
import com.service.school_service.model.SchoolClass;
import com.service.school_service.repository.SchoolClassRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SchoolClassServiceImpl implements SchoolClassService {
    private final SchoolClassRepository schoolClassRepository;
    private final TeacherClient teacherClient;
    private final StudentClient studentClient;

    private final SchoolClassMapper schoolClassMapper;

    public SchoolClassServiceImpl(SchoolClassRepository schoolClassRepository, TeacherClient teacherClient, StudentClient studentClient, SchoolClassMapper schoolClassMapper) {
        this.schoolClassRepository = schoolClassRepository;
        this.teacherClient = teacherClient;
        this.studentClient = studentClient;
        this.schoolClassMapper = schoolClassMapper;
    }

    @Override
    public SchoolClassDto createSchoolClass(CreateSchoolClassDto schoolClassDto) {
        SchoolClass schoolClass = schoolClassMapper.toEntity(schoolClassDto);
        SchoolClass savedClass = schoolClassRepository.save(schoolClass);
        return schoolClassMapper.toDto(savedClass);
    }

    @Override
    public SchoolClassDto getSchoolClass(Long id) {
        SchoolClass schoolClass = schoolClassRepository.findById(id)
                .orElseThrow(() -> new SchoolClassNotFoundException("School class not found with id: " + id));

        TeacherDto teacher = teacherClient.getTeacherById(schoolClass.getTeacherId());
        schoolClass.setTeacherDto(teacher);

        return schoolClassMapper.toDto(schoolClass);
    }

    @Override
    public List<SchoolClassDto> getAllSchoolClasses() {
        List<SchoolClass> schoolClasses = schoolClassRepository.findAll();
        return schoolClasses.stream().map(schoolClassMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public SchoolClassDto updateSchoolClass(Long id, SchoolClassDto updatedClassDto) {
        SchoolClass existingClass = schoolClassRepository.findById(id)
                .orElseThrow(() -> new SchoolClassNotFoundException("School class not found with id: " + id));

        updateSchoolClassDetails(existingClass, updatedClassDto);
        updateTeacherIfChanged(existingClass, updatedClassDto);
        updateStudentsIfChanged(existingClass, updatedClassDto);


        return schoolClassMapper.toDto(existingClass);
    }

    @Override
    public void deleteSchoolClass(Long id) {
        this.schoolClassRepository.deleteById(id);
    }

    private void updateSchoolClassDetails(SchoolClass existingClass, SchoolClassDto updatedClassDto) {
        existingClass.setName(updatedClassDto.name());
        existingClass.setGradeLevel(updatedClassDto.gradeLevel());
        existingClass.setLetter(updatedClassDto.letter());
        //do we want to be able to update the speciality ?
        }

    private void updateTeacherIfChanged(SchoolClass existingClass, SchoolClassDto dto) {
        Long newTeacherId = dto.teacherId();
        if (!existingClass.getTeacherId().equals(newTeacherId)) {
            TeacherDto teacherDto = teacherClient.getTeacherById(newTeacherId);
            existingClass.setTeacherId(newTeacherId);
            existingClass.setTeacherDto(teacherDto);
        }
    }

    private void updateStudentsIfChanged(SchoolClass existingClass, SchoolClassDto dto) {
        Set<Long> currentIds = extractStudentIds(existingClass.getStudents());
        Set<Long> newIds = extractStudentIds(dto.students());

        if (currentIds.equals(newIds)) return;

        Set<Long> toRemove = new HashSet<>(currentIds);
        toRemove.removeAll(newIds);

        Set<Long> toAdd = new HashSet<>(newIds);
        toAdd.removeAll(currentIds);

        studentClient.removeClassIdFromStudents(toRemove);
        studentClient.assignClassIdToStudents(toAdd, existingClass.getId());

        Set<StudentDto> updatedStudents = studentClient.getStudentsByIds(newIds);
        existingClass.setStudents(updatedStudents);
    }

    private Set<Long> extractStudentIds(Set<StudentDto> students) {
        return (students == null || students.isEmpty())
                ? Set.of()
                : students.stream().map(StudentDto::id).collect(Collectors.toSet());
    }
}
