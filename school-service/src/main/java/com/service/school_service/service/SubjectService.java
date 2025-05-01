package com.service.school_service.service;

import com.service.school_service.dto.CreateSubjectDto;
import com.service.school_service.dto.SubjectDto;

import java.util.List;

public interface SubjectService {
    SubjectDto createSubject(CreateSubjectDto subjectDto);
    SubjectDto updateSubject(Long id, SubjectDto subjectDto);
    void deleteSubject(Long id);
    SubjectDto getSubjectById(Long id);
    List<SubjectDto> getAllSubjects();
}
