package com.service.school_service.service;

import com.service.school_service.dto.CreateScheduleDto;
import com.service.school_service.dto.ScheduleDto;

public interface ScheduleService {

    ScheduleDto createSchedule(CreateScheduleDto programDto);
    //TODO: student get the schedule from school class schedule
    //TODO: iterate through all List<SubjAssignment> and assign them to teachers schedules
    ScheduleDto updateSchedule(Long id, ScheduleDto scheduleDto);
    ScheduleDto getScheduleById(Long id);
    void deleteSchedule(Long id);
}
