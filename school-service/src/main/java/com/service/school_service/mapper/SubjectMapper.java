package com.service.school_service.mapper;

import com.service.school_service.dto.CreateSubjectDto;
import com.service.school_service.dto.SubjectDto;
import com.service.school_service.model.Subject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    Subject toEntity(SubjectDto subjectDto);
    Subject toEntity(CreateSubjectDto subjectDto);
    SubjectDto toDto(Subject subject);
}