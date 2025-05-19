package com.service.school_service.service;

import com.service.school_service.dto.CreateScheduleDto;
import com.service.school_service.dto.ScheduleDto;
import com.service.school_service.exception.ScheduleNotFoundException;
import com.service.school_service.mapper.ScheduleMapper;
import com.service.school_service.mapper.SchoolClassMapper;
import com.service.school_service.model.Schedule;
import com.service.school_service.model.SchoolClass;
import com.service.school_service.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private final SchoolClassService schoolClassService;
    private final SchoolClassMapper schoolClassMapper;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, ScheduleMapper scheduleMapper, SchoolClassService schoolClassService, SchoolClassMapper schoolClassMapper) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
        this.schoolClassService = schoolClassService;
        this.schoolClassMapper = schoolClassMapper;
    }

    @Override
    @Transactional
    public ScheduleDto createSchedule(CreateScheduleDto dto) {
        // Use internal method to fetch managed entity
        SchoolClass schoolClass = schoolClassService.getEntityById(dto.schoolClassId());

        if (schoolClass.getSchedule() != null) {
            throw new IllegalStateException("SchoolClass already has a schedule assigned.");
        }

        Schedule schedule = new Schedule();
        schedule.setSchoolClass(schoolClass); //enough to update the SchoolClass.schedule

        Schedule saved = scheduleRepository.save(schedule);
        return scheduleMapper.toDto(saved);
    }

    @Override
    public ScheduleDto getScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException(id));

        return scheduleMapper.toDto(schedule);
    }

    @Override
    public void deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException(id));

        // Remove reference from school class if needed
        SchoolClass schoolClass = schedule.getSchoolClass();
        if (schoolClass != null) {
            schoolClass.setSchedule(null);
        }
        scheduleRepository.delete(schedule);
    }

    @Override
    @Transactional(readOnly = true)
    public Schedule getEntityById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException(id));
    }
}
