package com.service.school_service.controller;

import com.service.school_service.dto.CreateSubjectAssignmentDto;
import com.service.school_service.dto.SubjectAssignmentDto;
import com.service.school_service.service.SubjectAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subject-assignments")
@RequiredArgsConstructor
public class SubjectAssignmentController {
    private final SubjectAssignmentService subjectAssignmentService;

    @PostMapping
    public ResponseEntity<SubjectAssignmentDto> createSubjectAssignment(@RequestBody @Validated CreateSubjectAssignmentDto subjectAssignmentDto) {
        SubjectAssignmentDto created = subjectAssignmentService.createSubjectAssignment(subjectAssignmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectAssignmentDto> updateSubjectAssignment(Long id,@RequestBody @Validated SubjectAssignmentDto subjectAssignmentDto) {
        SubjectAssignmentDto updated = subjectAssignmentService.updateSubjectAssignment(id, subjectAssignmentDto);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SubjectAssignmentDto> deleteSubjectAssignment(@PathVariable Long id) {
        subjectAssignmentService.deleteSubjectAssignment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectAssignmentDto> getSubjectAssignmentById(@PathVariable Long id) {
        SubjectAssignmentDto subject = subjectAssignmentService.getSubjectAssignmentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(subject);
    }

    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<List<SubjectAssignmentDto>> getAllByScheduleId(@PathVariable Long scheduleId) {
        List<SubjectAssignmentDto> assignments = subjectAssignmentService.getAllSubjectAssignmentsByScheduleId(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(assignments);
    }

    }
