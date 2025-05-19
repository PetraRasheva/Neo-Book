package com.service.school_service.service;

import com.service.school_service.dto.CreateScheduleDto;
import com.service.school_service.dto.ScheduleDto;
import com.service.school_service.model.Schedule;

public interface ScheduleService {

    ScheduleDto createSchedule(CreateScheduleDto CreateScheduleDto);
    //TODO: student get the schedule from school class schedule
    //TODO: iterate through all List<SubjAssignment> and assign them to teachers schedules

    //ScheduleDto updateSchedule(Long id, ScheduleDto scheduleDto); // we do not need it
    ScheduleDto getScheduleById(Long id);
    void deleteSchedule(Long id);

    Schedule getEntityById(Long id);
}
