package com.service.school_service.mapper;

import com.service.school_service.dto.CreateSubjectAssignmentDto;
import com.service.school_service.dto.SubjectAssignmentDto;
import com.service.school_service.model.SubjectAssignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper( componentModel = "spring")
public interface SubjectAssignmentMapper {

    @Mapping(target="id", ignore = true)
    SubjectAssignment toEntity(CreateSubjectAssignmentDto subjectAssignmentDto);

    @Mapping(target="id", ignore = true)
    SubjectAssignment toEntity(SubjectAssignmentDto subjectAssignmentDto);

    SubjectAssignmentDto toDto(SubjectAssignment subjectAssignment);
}
