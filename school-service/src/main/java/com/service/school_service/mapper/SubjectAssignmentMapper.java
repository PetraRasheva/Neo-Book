package com.service.school_service.mapper;

import com.service.school_service.dto.CreateSubjectAssignmentDto;
import com.service.school_service.dto.SubjectAssignmentDto;
import com.service.school_service.model.SubjectAssignment;
import org.mapstruct.Mapping;

public interface SubjectAssignmentMapper {

    SubjectAssignment toEntity(CreateSubjectAssignmentDto subjectAssignmentDto);
    SubjectAssignment toEntity(SubjectAssignmentDto subjectAssignmentDto);
    SubjectAssignmentDto toDto(SubjectAssignment subjectAssignment);

}
