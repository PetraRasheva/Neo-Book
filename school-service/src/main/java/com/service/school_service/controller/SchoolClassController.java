package com.service.school_service.controller;

import com.service.school_service.dto.CreateSchoolClassDto;
import com.service.school_service.dto.SchoolClassDto;
import com.service.school_service.service.SchoolClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/school-classes")
public class SchoolClassController {

    private final SchoolClassService schoolClassService;

    public SchoolClassController(SchoolClassService schoolClassService) {
        this.schoolClassService = schoolClassService;
    }

    @GetMapping("/{classId}")
    public Mono<ResponseEntity<SchoolClassDto>> getSchoolClassWithDetails(@PathVariable Long classId) {
        return schoolClassService.getSchoolClass(classId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<SchoolClassDto>> createSchoolClass(@RequestBody CreateSchoolClassDto schoolClass) {
        return schoolClassService.createSchoolClass(schoolClass)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{classId}")
    public Mono<ResponseEntity<Void>> deleteSchoolClassById(@PathVariable Long classId) {
        return schoolClassService.deleteSchoolClass(classId)
                .thenReturn(ResponseEntity.noContent().build());
    }

    @PutMapping("/{classId}")
    public Mono<ResponseEntity<SchoolClassDto>> updateSchoolClass(@PathVariable Long classId, @RequestBody SchoolClassDto schoolClass) {
        return schoolClassService.updateSchoolClass(classId, schoolClass)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @GetMapping("/all")
    public Flux<SchoolClassDto> getAllSchoolClasses() {
        return schoolClassService.getAllSchoolClasses();
    }
}
