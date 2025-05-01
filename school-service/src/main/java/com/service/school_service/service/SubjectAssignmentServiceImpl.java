package com.service.school_service.service;

import com.service.school_service.dto.CreateSubjectAssignmentDto;
import com.service.school_service.dto.SubjectAssignmentDto;
import com.service.school_service.mapper.SubjectAssignmentMapper;
import com.service.school_service.model.SubjectAssignment;
import com.service.school_service.repository.SubjectAssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectAssignmentServiceImpl implements SubjectAssignmentService {

    private final SubjectAssignmentRepository subjectAssignmentRepository;
    private final SubjectAssignmentMapper subjectAssignmentMapper;

    public SubjectAssignmentServiceImpl(SubjectAssignmentRepository subjectAssignmentRepository, SubjectAssignmentMapper subjectAssignmentMapper) {
        this.subjectAssignmentRepository = subjectAssignmentRepository;
        this.subjectAssignmentMapper = subjectAssignmentMapper;
    }

    @Override
    public SubjectAssignmentDto createSubjectAssignment(CreateSubjectAssignmentDto subjectAssignmentDto) {
        SubjectAssignment subjectAssignment = subjectAssignmentMapper.toEntity(subjectAssignmentDto);
        return this.subjectAssignmentMapper.toDto(subjectAssignmentRepository.save(subjectAssignment));
    }

    @Override
    public SubjectAssignmentDto updateSubjectAssignment(Long id, SubjectAssignmentDto subjectAssignmentDto) {
        SubjectAssignment currentSubjectAssignment = this.subjectAssignmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Subject Assignment Not Found"));

        // TODO: Program service Subject Service
        return null;
    }

    @Override
    public Optional<List<SubjectAssignmentDto>> getAllSubjectAssignmentsByProgramId(Long programId) {
        return Optional.empty();
    }

    @Override
    public SubjectAssignmentDto getSubjectAssignmentById(Long id) {
        return null;
    }

    @Override
    public void deleteSubjectAssignment(Long id) {

    }
}
