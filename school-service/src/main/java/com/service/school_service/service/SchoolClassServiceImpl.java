package com.service.school_service.service;

import com.service.school_service.client.StudentClient;
import com.service.school_service.client.TeacherClient;
import com.service.school_service.dto.*;
import com.service.school_service.exception.SchoolClassNotFoundException;
import com.service.school_service.mapper.*;
import com.service.school_service.model.Schedule;
import com.service.school_service.model.SchoolClass;
import com.service.school_service.repository.SchoolClassRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SchoolClassServiceImpl implements SchoolClassService {
    private final SchoolClassRepository schoolClassRepository;
    private final TeacherClient teacherClient;
    private final StudentClient studentClient;
    private final SchoolClassMapper schoolClassMapper;
    private final SchoolService schoolService;
    private final SpecialityService specialityService;
    private final ScheduleMapper scheduleMapper;
    private final SchoolMapper schoolMapper;
    private final SpecialityMapper specialityMapper;

    public SchoolClassServiceImpl(SchoolClassRepository schoolClassRepository, TeacherClient teacherClient, StudentClient studentClient, SchoolClassMapper schoolClassMapper, SchoolService schoolService, SpecialityService specialityService, ScheduleMapper scheduleMapper, SchoolMapper schoolMapper, SpecialityMapper specialityMapper) {
        this.schoolClassRepository = schoolClassRepository;
        this.teacherClient = teacherClient;
        this.studentClient = studentClient;
        this.schoolClassMapper = schoolClassMapper;
        this.schoolService = schoolService;
        this.specialityService = specialityService;
        this.scheduleMapper = scheduleMapper;
        this.schoolMapper = schoolMapper;
        this.specialityMapper = specialityMapper;
    }

    @Override
    public SchoolClassDto createSchoolClass(CreateSchoolClassDto schoolClassDto) {
        SchoolDto school = schoolService.getSchoolById(schoolClassDto.schoolId());

        SpecialityDto speciality = specialityService.getSpecialityById(schoolClassDto.specialityId());
        // Map the DTO to entity
        SchoolClass schoolClass = schoolClassMapper.toEntity(schoolClassDto);

        // Attach the fetched school and speciality to the school class
        schoolClass.setSchool(this.schoolMapper.toEntity(school)); //Is this the right approach ?
        schoolClass.setSpeciality(this.specialityMapper.toEntity(speciality));

        // Save the school class
        SchoolClass savedClass = schoolClassRepository.save(schoolClass);

        // Return the saved class DTO
        return schoolClassMapper.toDto(savedClass);
    }

    @Override
    public SchoolClassDto getSchoolClassById(Long id) {
        SchoolClass schoolClass = schoolClassRepository.findById(id)
                .orElseThrow(() -> new SchoolClassNotFoundException(id));

        // Fetch teacher details
        if (schoolClass.getTeacherId() != null) {
            TeacherDto teacherDto = teacherClient.getTeacherById(schoolClass.getTeacherId());
            schoolClass.setTeacherDto(teacherDto);
        }

        // Fetch students in this class
        Set<StudentDto> students = studentClient.getStudentsByClassId(schoolClass.getId());
        schoolClass.setStudents(students);

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
                .orElseThrow(() -> new SchoolClassNotFoundException(id));

        updateSchoolClassDetails(existingClass, updatedClassDto);
        updateTeacherIfChanged(existingClass, updatedClassDto);

        SchoolClass saved = schoolClassRepository.save(existingClass);
        return schoolClassMapper.toDto(saved);
    }

    private void updateSchoolClassDetails(SchoolClass existingClass, SchoolClassDto updatedClassDto) {
        existingClass.setGradeLevel(updatedClassDto.gradeLevel());
        existingClass.setLetter(updatedClassDto.letter());
        //existingClass.setSpeciality();
        //do we want to be able to update the speciality ?
    }

    private void updateTeacherIfChanged(SchoolClass existingClass, SchoolClassDto dto) {
        UUID newTeacherId = dto.teacherId();
        if (!existingClass.getTeacherId().equals(newTeacherId)) {
            TeacherDto teacherDto = teacherClient.getTeacherById(newTeacherId);
            existingClass.setTeacherId(newTeacherId);
            existingClass.setTeacherDto(teacherDto);
        }
    }

    @Override
    public ScheduleDto getScheduleBySchoolClassId(Long id) {
        SchoolClass existingClass = schoolClassRepository.findById(id)
                .orElseThrow(() -> new SchoolClassNotFoundException(id));

        Schedule schedule = existingClass.getSchedule();
        return this.scheduleMapper.toDto(schedule);
    }

    @Override
    public SchoolClass getEntityById(Long id) {
        return schoolClassRepository.findById(id)
                .orElseThrow(() -> new SchoolClassNotFoundException(id));
    }


    @Override
    public void deleteSchoolClass(Long id) { //TODO: pyrwo mestish ili iztrivash
        this.schoolClassRepository.deleteById(id);
    }

    @Override
    public void assignStudent(Long classId, UUID studentId) {
        SchoolClass schoolClass = this.schoolClassRepository.findById(classId)
                .orElseThrow(() -> new SchoolClassNotFoundException(classId));
        StudentDto studentDto = this.studentClient.getStudentById(studentId);
        schoolClass.assignStudent(studentDto);

        this.studentClient.assignStudentToClass(studentId, classId); //TODO: follow up in user-service
    }

    @Override
    public void unassignStudent(Long classId, UUID studentId) {
        SchoolClass schoolClass = this.schoolClassRepository
                .findById(classId).orElseThrow(() -> new SchoolClassNotFoundException(classId));
        StudentDto studentDto = this.studentClient.getStudentById(studentId);
        schoolClass.unassignStudent(studentDto);

        this.studentClient.unassignStudentToClass(studentId, classId); //TODO: follow up in user-service
    }
}
