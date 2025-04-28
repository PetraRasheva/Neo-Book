package com.service.school_service.service;

import com.service.school_service.dto.CreateSchoolClassDto;
import com.service.school_service.dto.SchoolClassDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SchoolClassService {
    Mono<SchoolClassDto> createSchoolClass(CreateSchoolClassDto schoolClass);
    Mono<SchoolClassDto> getSchoolClass(Long classId);
    Flux<SchoolClassDto> getAllSchoolClasses();
    Mono<Void> deleteSchoolClass(Long id);
    Mono<SchoolClassDto> updateSchoolClass(Long id, SchoolClassDto updatedClass);
}
