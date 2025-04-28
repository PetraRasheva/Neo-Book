package com.service.school_service.mapper;

import com.service.school_service.dto.CreateSchoolClassDto;
import com.service.school_service.dto.SchoolClassDto;
import com.service.school_service.model.School;
import com.service.school_service.model.SchoolClass;
import com.service.school_service.model.Speciality;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper( componentModel = "spring")
public interface SchoolClassMapper {
    @Mapping(target = "school", source = "schoolId", qualifiedByName = "idToSchool")
    @Mapping(target = "speciality", source = "specialityId", qualifiedByName = "idToSpeciality")
    @Mapping(target = "teacherDto", ignore = true) // ignore transient field on toEntity
    @Mapping(target = "students", ignore = true) // ignore transient field on toEntity
    SchoolClass toEntity(SchoolClassDto schoolClassDto);

    @Mapping(target = "id", ignore = true) // because it's auto-generated
    @Mapping(target = "students", ignore = true) // we create school empty first
    SchoolClass toEntity(CreateSchoolClassDto createSchoolClassDto);

    @Mapping(target = "schoolId", source = "school.id")
    @Mapping(target = "specialityId", source = "speciality.id")
    // Map transient fields normally
    SchoolClassDto toDto(SchoolClass schoolClass);

    // Custom methods to convert ids to entities - you can implement these or inject services for real lookups
    @Named("idToSchool")
    default School idToSchool(Long id) {
        if (id == null) return null;
        School school = new School();
        school.setId(id);
        return school;
    }

    @Named("idToSpeciality")
    default Speciality idToSpeciality(Long id) {
        if (id == null) return null;
        Speciality speciality = new Speciality();
        speciality.setId(id);
        return speciality;
    }

}
