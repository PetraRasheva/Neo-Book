package com.service.school_service.service;

import com.service.school_service.client.TeacherClient;
import com.service.school_service.dto.*;
import com.service.school_service.exception.SubjectAssignmentNotFoundException;
import com.service.school_service.mapper.ScheduleMapper;
import com.service.school_service.mapper.SubjectAssignmentMapper;
import com.service.school_service.mapper.SubjectMapper;
import com.service.school_service.model.Schedule;
import com.service.school_service.model.Subject;
import com.service.school_service.model.SubjectAssignment;
import com.service.school_service.repository.SubjectAssignmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubjectAssignmentServiceImpl implements SubjectAssignmentService {

    private final SubjectAssignmentRepository subjectAssignmentRepository;
    private final SubjectAssignmentMapper subjectAssignmentMapper;
    private final TeacherClient teacherClient;
    private final SubjectService subjectService;
    private final ScheduleService scheduleService;

    public SubjectAssignmentServiceImpl(SubjectAssignmentRepository subjectAssignmentRepository, SubjectAssignmentMapper subjectAssignmentMapper, TeacherClient teacherClient, SubjectService subjectService, ScheduleService scheduleService) {
        this.subjectAssignmentRepository = subjectAssignmentRepository;
        this.subjectAssignmentMapper = subjectAssignmentMapper;
        this.teacherClient = teacherClient;
        this.subjectService = subjectService;
        this.scheduleService = scheduleService;
    }

    @Override
    @Transactional
    public SubjectAssignmentDto createSubjectAssignment(CreateSubjectAssignmentDto dto) {
        // Get the related entities via service methods that expose entities
        Subject subject = subjectService.getEntityById(dto.subjectId());
        Schedule schedule = scheduleService.getEntityById(dto.scheduleId());

        // Get teacher via client (remains as DTO, handled as @Transient)
//        TeacherDto teacher = teacherClient.getTeacherById(dto.teacherId());
//        if (teacher == null) {
//            throw new IllegalArgumentException("Teacher not found with ID: " + dto.teacherId());
//        }

        // Create and populate the assignment
        SubjectAssignment assignment = new SubjectAssignment();
        assignment.setDayOfWeek(dto.dayOfWeek());
        assignment.setTimeSlot(dto.timeSlot());
        assignment.setTeacherId(dto.teacherId());
        //assignment.setTeacher(teacher); // Transient
        assignment.setSubject(subject);
        assignment.setSchedule(schedule);

        // Save and return
        SubjectAssignment saved = subjectAssignmentRepository.save(assignment);
        return subjectAssignmentMapper.toDto(saved);
    }

    @Override
    public SubjectAssignmentDto updateSubjectAssignment(Long id, SubjectAssignmentDto dto) {
        SubjectAssignment assignment = subjectAssignmentRepository.findById(id)
                .orElseThrow(() -> new SubjectAssignmentNotFoundException(id));

        TeacherDto teacher = teacherClient.getTeacherById(dto.teacherId());

        SubjectDto subjectDto = subjectService.getSubjectById(dto.subjectId());

        assignment.setDayOfWeek(dto.dayOfWeek());
        assignment.setTimeSlot(dto.timeSlot());
        assignment.setTeacherId(dto.teacherId());
        assignment.setTeacher(teacher); // @Transient

        Subject subject = new Subject();
        subject.setId(subjectDto.id());
        assignment.setSubject(subject);

        SubjectAssignment updated = subjectAssignmentRepository.save(assignment);
        return subjectAssignmentMapper.toDto(updated);
    }

    @Override
    public List<SubjectAssignmentDto> getAllSubjectAssignmentsByScheduleId(Long scheduleId) {
        List<SubjectAssignment> assignments = subjectAssignmentRepository.findByScheduleId(scheduleId);
        if (assignments.isEmpty()) {
            throw new SubjectAssignmentNotFoundException(scheduleId);
        }
        return assignments.stream()
                .map(subjectAssignmentMapper::toDto)
                .toList();
    }

    @Override
    public SubjectAssignmentDto getSubjectAssignmentById(Long id) {
        SubjectAssignment assignment = subjectAssignmentRepository.findById(id)
                .orElseThrow(() -> new SubjectAssignmentNotFoundException(id));
        return subjectAssignmentMapper.toDto(assignment);
    }

    @Override
    public void deleteSubjectAssignment(Long id) {
        if (!subjectAssignmentRepository.existsById(id)) {
            throw new SubjectAssignmentNotFoundException(id);
        }
        subjectAssignmentRepository.deleteById(id);
    }
}
