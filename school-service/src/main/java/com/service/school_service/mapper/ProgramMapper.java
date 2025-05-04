package com.service.school_service.mapper;

import com.service.school_service.dto.CreateProgramDto;
import com.service.school_service.dto.ProgramDto;
import com.service.school_service.model.Program;
import com.service.school_service.model.SchoolClass;
import com.service.school_service.model.SubjectAssignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper( componentModel = "spring")
public interface ProgramMapper {

    //TODO: how to map subjAssignments

    @Mapping(target="id", ignore = true)
    @Mapping(target= "schoolClass" , source="SchoolClassId", qualifiedByName = "idToSchoolClass")
    @Mapping(target = "subjectAssignments", source = "subjectAssignmentIds", qualifiedByName = "idsToSubjectAssignments")
    Program toEntity(ProgramDto programDto);

    @Mapping(target="id", ignore = true)
    @Mapping(target= "schoolClass" , source="SchoolClassId", qualifiedByName = "idToSchoolClass")
//    @Mapping(target = "subjectAssignments", source = "subjectAssignmentIds", qualifiedByName = "idsToSubjectAssignments")
    Program toEntity(CreateProgramDto programDto);

//    @Mapping(target = "subjectAssignments", source = "subjectAssignments")
    ProgramDto toDto(Program program);

    @Named("idToSchoolClass")
    default SchoolClass idToSchoolClass(Long id) {
        if (id == null) return null;
        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setId(id);
        return schoolClass;
    }

    @Named("idsToSubjectAssignments")
    default List<SubjectAssignment> idsToSubjectAssignments(List<Long> ids) {
        if (ids == null) return new ArrayList<>();
        return ids.stream().map((id) -> {
            SubjectAssignment subjectAssignment = new SubjectAssignment();
            subjectAssignment.setId(id);
            return subjectAssignment;
        }).collect(Collectors.toList());
    }
}
