package com.service.school_service.service;

import com.service.school_service.dto.CreateSubjectDto;
import com.service.school_service.dto.SubjectDto;
import com.service.school_service.mapper.SubjectMapper;
import com.service.school_service.model.Subject;
import com.service.school_service.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    @Override
    public SubjectDto createSubject(CreateSubjectDto subjectDto) {
        Subject subject = subjectMapper.toEntity(subjectDto);
        return subjectMapper.toDto(subjectRepository.save(subject));
    }

    @Override
    public SubjectDto updateSubject(Long id, SubjectDto subjectDto) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        subject.setName(subjectDto.name());
        return subjectMapper.toDto(subjectRepository.save(subject));
    }

    @Override
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }

    @Override
    public SubjectDto getSubjectById(Long id) {
        return subjectMapper.toDto(subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found")));
    }

    @Override
    public List<SubjectDto> getAllSubjects() {
        return this.subjectRepository.findAll().stream().map(subjectMapper::toDto).collect(Collectors.toList());
    }
}
