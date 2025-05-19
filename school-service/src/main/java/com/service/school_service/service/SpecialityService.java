package com.service.school_service.service;

import com.service.school_service.dto.CreateSpecialityDto;
import com.service.school_service.dto.SpecialityDto;
import com.service.school_service.model.Speciality;

import java.util.List;

public interface SpecialityService {
    SpecialityDto createSpeciality(CreateSpecialityDto dto);
    SpecialityDto updateSpeciality(Long id, SpecialityDto dto);
    void deleteSpeciality(Long id);
    SpecialityDto getSpecialityById(Long id);
    List<SpecialityDto> getAllSpecialities();
    Speciality getEntityById(Long id);
}
