package com.service.school_service.repository;

import com.service.school_service.model.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
    List<SchoolClass> findBySchoolId(Long schoolId);}

