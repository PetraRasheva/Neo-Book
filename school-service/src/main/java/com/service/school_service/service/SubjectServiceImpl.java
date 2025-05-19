package com.service.school_service.service;

import com.service.school_service.dto.CreateSubjectDto;
import com.service.school_service.dto.SubjectDto;
import com.service.school_service.exception.SubjectNotFoundException;
import com.service.school_service.mapper.SubjectMapper;
import com.service.school_service.model.Subject;
import com.service.school_service.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    @Override
    @Transactional
    public SubjectDto createSubject(CreateSubjectDto subjectDto) {
        Subject subject = subjectMapper.toEntity(subjectDto);
        subject.setTeacherIds(new HashSet<>(subjectDto.teacherIds()));
        return subjectMapper.toDto(subjectRepository.save(subject));
    }

    @Override
    public SubjectDto updateSubject(Long id, SubjectDto subjectDto) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new SubjectNotFoundException(id));

        subject.setName(subjectDto.name());
        subject.setTeacherIds(new HashSet<>(subjectDto.teacherIds()));
        return subjectMapper.toDto(subjectRepository.save(subject));
    }

    @Override
    public void deleteSubject(Long id) {
        if (!subjectRepository.existsById(id)) {
            throw new SubjectNotFoundException(id);
        }
        subjectRepository.deleteById(id);
    }

    @Override
    public SubjectDto getSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new SubjectNotFoundException(id));
        return subjectMapper.toDto(subject);
    }

    @Override
    public List<SubjectDto> getAllSubjects() {
        return this.subjectRepository.findAll().stream().map(subjectMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Subject getEntityById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new SubjectNotFoundException(id));
    }
}
