package com.service.school_service.controller;

import com.service.school_service.dto.CreateSpecialityDto;
import com.service.school_service.dto.SpecialityDto;
import com.service.school_service.service.SpecialityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specialities")
@RequiredArgsConstructor
public class SpecialityController {

    private final SpecialityService specialityService;

    @PostMapping
    public ResponseEntity<SpecialityDto> createSpeciality(@RequestBody @Validated CreateSpecialityDto createDto) {
        SpecialityDto created = specialityService.createSpeciality(createDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialityDto> updateSpeciality(@PathVariable Long id, @RequestBody @Validated SpecialityDto dto) {
        SpecialityDto updated = specialityService.updateSpeciality(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpeciality(@PathVariable Long id) {
        specialityService.deleteSpeciality(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialityDto> getById(@PathVariable Long id) {
        SpecialityDto dto = specialityService.getSpecialityById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<SpecialityDto>> getAll() {
        List<SpecialityDto> list = specialityService.getAllSpecialities();
        return ResponseEntity.ok(list);
    }
}
