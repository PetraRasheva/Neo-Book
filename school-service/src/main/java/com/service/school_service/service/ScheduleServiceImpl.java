package com.service.school_service.service;

import com.service.school_service.dto.CreateScheduleDto;
import com.service.school_service.dto.ScheduleDto;
import com.service.school_service.dto.SchoolClassDto;
import com.service.school_service.exception.ScheduleNotFoundException;
import com.service.school_service.mapper.ScheduleMapper;
import com.service.school_service.mapper.SchoolClassMapper;
import com.service.school_service.model.Schedule;
import com.service.school_service.model.SchoolClass;
import com.service.school_service.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
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
    @Transactional
    public ScheduleDto createSchedule(Long schoolClassId) {
        // Use internal method to fetch managed entity
        SchoolClass schoolClass = schoolClassService.getEntityById(schoolClassId);

        if (schoolClass.getSchedule() != null) {
            throw new IllegalStateException("SchoolClass already has a schedule assigned.");
        }

        Schedule schedule = new Schedule();
        schedule.setSchoolClass(schoolClass);

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
}
