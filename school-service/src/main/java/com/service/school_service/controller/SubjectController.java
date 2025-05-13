package com.service.school_service.controller;

import com.service.school_service.dto.CreateSubjectDto;
import com.service.school_service.dto.SubjectDto;
import com.service.school_service.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<SubjectDto> createSubject(@RequestBody @Validated CreateSubjectDto subjectDto) {
        SubjectDto created = subjectService.createSubject(subjectDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectDto> updateSubject(@PathVariable Long id, @RequestBody @Validated SubjectDto subjectDto) {
        SubjectDto updated = subjectService.updateSubject(id, subjectDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDto> getSubjectById(@PathVariable Long id) {
        SubjectDto subject = subjectService.getSubjectById(id);
        return ResponseEntity.ok(subject);
    }

    @GetMapping
    public ResponseEntity<List<SubjectDto>> getAllSubjects() {
        List<SubjectDto> subjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }
}
