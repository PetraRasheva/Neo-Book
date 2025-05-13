package com.service.school_service.mapper;

import com.service.school_service.dto.CreateScheduleDto;
import com.service.school_service.dto.ScheduleDto;
import com.service.school_service.model.Schedule;
import com.service.school_service.model.SchoolClass;
import com.service.school_service.model.SubjectAssignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subjectAssignments", ignore = true)
    @Mapping(target = "schoolClass", ignore = true)
    Schedule toEntity(ScheduleDto scheduleDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subjectAssignments", ignore = true)
    @Mapping(target = "schoolClass", source = "schoolClassId")
    Schedule toEntity(CreateScheduleDto dto);

    ScheduleDto toDto(Schedule schedule);
}
