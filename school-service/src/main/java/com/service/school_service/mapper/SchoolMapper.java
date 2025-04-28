package com.service.school_service.mapper;

import com.service.school_service.dto.CreateSchoolDto;
import com.service.school_service.dto.SchoolDto;
import com.service.school_service.model.School;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SchoolMapper {

    @Mapping(target = "id", ignore = true)        // Ignore setting 'id' in the entity because DB generates it
    @Mapping(target = "classes", ignore = true)   // Ignore setting 'classes' in the entity when mapping from DTO
    School toEntity(SchoolDto schoolDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "classes", ignore = true)
    School toEntity(CreateSchoolDto createSchoolDto);

    SchoolDto toDto(School school);
}
