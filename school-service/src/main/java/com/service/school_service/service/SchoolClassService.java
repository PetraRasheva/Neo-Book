package com.service.school_service.service;

import com.service.school_service.model.SchoolClass;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SchoolClassService {
    Mono<SchoolClass> getSchoolClass(Long classId);
    Flux<SchoolClass> getAllSchoolClasses();
    Mono<Void> deleteSchoolClass(Long id);
    Mono<SchoolClass> updateSchoolClass(Long id, SchoolClass updatedClass);
    Mono<SchoolClass> createSchoolClass(SchoolClass schoolClass);

}
