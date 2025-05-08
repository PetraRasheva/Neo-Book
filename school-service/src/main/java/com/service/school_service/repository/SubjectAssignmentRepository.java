package com.service.school_service.repository;

import com.service.school_service.enums.TimeSlot;
import com.service.school_service.model.SubjectAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.util.List;

public interface SubjectAssignmentRepository extends JpaRepository<SubjectAssignment, Long> {
    @Query("""
        SELECT sa.timeSlot FROM SubjectAssignment sa
        WHERE sa.schedule.id = :scheduleId AND sa.dayOfWeek = :dayOfWeek
    """)
    List<TimeSlot> findUsedTimeSlots(@Param("scheduleId") Long scheduleId,
                                     @Param("dayOfWeek") DayOfWeek dayOfWeek);

    List<SubjectAssignment> findByScheduleId(Long scheduleId);
}
