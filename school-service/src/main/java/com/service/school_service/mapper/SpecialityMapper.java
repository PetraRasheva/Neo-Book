package com.service.school_service.mapper;

import com.service.school_service.dto.CreateSpecialityDto;
import com.service.school_service.dto.SpecialityDto;
import com.service.school_service.model.Speciality;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpecialityMapper {
    Speciality toEntity(CreateSpecialityDto dto);

    Speciality toEntity(SpecialityDto dto);

    SpecialityDto toDto(Speciality entity);
}
