package com.service.school_service.controller;

import com.service.school_service.dto.CreateSchoolClassDto;
import com.service.school_service.dto.ScheduleDto;
import com.service.school_service.dto.SchoolClassDto;
import com.service.school_service.service.SchoolClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/school-classes")
@RequiredArgsConstructor
public class SchoolClassController {

    private final SchoolClassService schoolClassService;

    @GetMapping("/{classId}")
    public ResponseEntity<SchoolClassDto> getSchoolClassWithDetails(@PathVariable Long classId) {
        SchoolClassDto schoolClassDto = schoolClassService.getSchoolClassById(classId);
        return new ResponseEntity<>(schoolClassDto, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<SchoolClassDto> createSchoolClass(@RequestBody @Validated CreateSchoolClassDto schoolClass) {
        SchoolClassDto schoolClassDto = schoolClassService.createSchoolClass(schoolClass);
        return new ResponseEntity<>(schoolClassDto, HttpStatus.CREATED);

    }

    @DeleteMapping("/{classId}")
    public ResponseEntity<Void> deleteSchoolClassById(@PathVariable Long classId) {
        this.schoolClassService.deleteSchoolClass(classId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/{classId}")
    public ResponseEntity<SchoolClassDto> updateSchoolClass(@PathVariable Long classId, @RequestBody @Validated SchoolClassDto schoolClass) {
        SchoolClassDto schoolClassDto = schoolClassService.updateSchoolClass(classId, schoolClass);
        return new ResponseEntity<>(schoolClassDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SchoolClassDto>> getAllSchoolClasses() {
        List<SchoolClassDto> schoolClassDtos = schoolClassService.getAllSchoolClasses();
        return new ResponseEntity<>(schoolClassDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}/schedule")
    public ResponseEntity<ScheduleDto> getSchedule(@PathVariable Long id) {
        ScheduleDto scheduleDto = this.schoolClassService.getScheduleBySchoolClassId(id);
        return new ResponseEntity<>(scheduleDto, HttpStatus.OK);
    }
}
