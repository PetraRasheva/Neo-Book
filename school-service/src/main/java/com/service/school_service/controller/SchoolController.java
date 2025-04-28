package com.service.school_service.controller;

import com.service.school_service.dto.CreateSchoolDto;
import com.service.school_service.dto.SchoolDto;
import com.service.school_service.service.SchoolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class SchoolController {
    
    private final SchoolService schoolService;
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping
    public ResponseEntity<SchoolDto> createSchool(@RequestBody CreateSchoolDto school) {
       SchoolDto schoolDto =  schoolService.createSchool(school);
       return ResponseEntity.ok(schoolDto);
    }

    @GetMapping("/{classId}")
    public ResponseEntity<SchoolDto> getSchoolWithDetails(@PathVariable Long classId) {
        Optional<SchoolDto> schoolDtoOpt = schoolService.getSchoolById(classId);
        return schoolDtoOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/{classId}")
    public ResponseEntity<SchoolDto> updateSchool(@PathVariable Long classId, @RequestBody SchoolDto school) {
        try {
            SchoolDto updatedSchool = schoolService.updateSchool(classId, school);
            return ResponseEntity.ok(updatedSchool);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<SchoolDto>> getAllSchools() {
        List<SchoolDto> schools = schoolService.getAllSchools();
        if(schools.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(schools);
    }

    @DeleteMapping("/{classId}")
    public ResponseEntity<Void> deleteSchoolById(@PathVariable Long classId) {
        schoolService.deleteSchoolById(classId);
        return ResponseEntity.noContent().build();
    }
}
