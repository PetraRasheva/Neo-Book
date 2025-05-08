package com.service.school_service.service;

import com.service.school_service.dto.CreateScheduleDto;
import com.service.school_service.dto.ScheduleDto;
import com.service.school_service.exception.ScheduleNotFoundException;
import com.service.school_service.mapper.ScheduleMapper;
import com.service.school_service.model.Schedule;
import com.service.school_service.model.SchoolClass;
import com.service.school_service.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private final SchoolClassService schoolClassService;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, ScheduleMapper scheduleMapper, SchoolClassService schoolClassService) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
        this.schoolClassService = schoolClassService;
    }

    @Override
    public ScheduleDto createSchedule(CreateScheduleDto scheduleDto) {
        SchoolClass schoolClass = schoolClassService.getEntityById(scheduleDto.schoolClassId()); //Is this the right approach ??

        Schedule schedule = new Schedule();
        schedule.setSchoolClass(schoolClass);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return scheduleMapper.toDto(savedSchedule);
    }

    @Override
    public ScheduleDto updateSchedule(Long id, ScheduleDto scheduleDto) {
        Schedule existing = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException(id));

        Schedule updated = scheduleMapper.toEntity(scheduleDto);
        updated.setId(existing.getId());
        updated.setSchoolClass(existing.getSchoolClass());

        return scheduleMapper.toDto(scheduleRepository.save(updated));
    }

    @Override
    public ScheduleDto getScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException(id));

        return scheduleMapper.toDto(schedule);
    }

    @Override
    public void deleteSchedule(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new ScheduleNotFoundException(id);
        }
        scheduleRepository.deleteById(id);
    }
}
