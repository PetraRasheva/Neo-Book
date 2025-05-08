package com.service.school_service.service;

import com.service.school_service.dto.CreateSubjectAssignmentDto;
import com.service.school_service.dto.SubjectAssignmentDto;

import java.util.List;
import java.util.Optional;

public interface SubjectAssignmentService {
    SubjectAssignmentDto createSubjectAssignment(CreateSubjectAssignmentDto subjectAssignmentDto);
    SubjectAssignmentDto updateSubjectAssignment(Long id, SubjectAssignmentDto subjectAssignmentDto);
    List<SubjectAssignmentDto> getAllSubjectAssignmentsByScheduleId(Long id);
    SubjectAssignmentDto getSubjectAssignmentById(Long id);
    void deleteSubjectAssignment(Long id);
}
