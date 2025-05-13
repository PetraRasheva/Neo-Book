package com.service.school_service.controller;

import com.service.school_service.dto.*;
import com.service.school_service.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
@RequiredArgsConstructor
public class SchoolController {
    
    private final SchoolService schoolService;

    @PostMapping
    public ResponseEntity<SchoolDto> createSchool(@RequestBody @Validated CreateSchoolDto schoolDto) {
        SchoolDto createdSchool = schoolService.createSchool(schoolDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSchool);
    }

    @GetMapping("/{schoolId}")
    public ResponseEntity<SchoolDto> getSchoolById(@PathVariable Long schoolId) {
        SchoolDto school = schoolService.getSchoolById(schoolId);
        return ResponseEntity.status(HttpStatus.OK).body(school);
    }

    @PutMapping("/{schoolId}")
    public ResponseEntity<SchoolDto> updateSchool(@PathVariable Long schoolId, @RequestBody @Validated UpdateSchoolDto schoolDto) {
        SchoolDto updatedSchool = schoolService.updateSchool(schoolId, schoolDto);
        return ResponseEntity.ok(updatedSchool);
    }

    @GetMapping
    public ResponseEntity<List<SchoolDto>> getAllSchools() {
        List<SchoolDto> schools = schoolService.getAllSchools();
        return schools.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(schools);
    }

    @DeleteMapping("/{schoolId}")
    public ResponseEntity<Void> deleteSchool(@PathVariable Long schoolId) {
        schoolService.deleteSchoolById(schoolId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{schoolId}/classes")
    public ResponseEntity<SchoolClassDto> addClassToSchool(@PathVariable Long schoolId, @RequestBody @Validated CreateSchoolClassDto dto) {
        SchoolClassDto addedClass = schoolService.addClassToSchool(schoolId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedClass);
    }

    @DeleteMapping("/{schoolId}/classes/{classId}")
    public ResponseEntity<Void> removeClassFromSchool(@PathVariable Long schoolId, @PathVariable Long classId) {
        schoolService.removeClassFromSchool(schoolId, classId);
        return ResponseEntity.noContent().build();
    }
}
