package com.service.school_service.model;

import com.service.school_service.dto.TeacherDto;
import com.service.school_service.enums.TimeSlot;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class SubjectAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 1)
    private DayOfWeek dayOfWeek; // Enum: MONDAY, TUESDAY, etc.

    // 2)  вземи тайм слотовете, които са свободни за конкретния dayOfWeek
    private TimeSlot timeSlot;

    // 4) Вземи учител за конкретен Subj, който няма часове в този dayOfWeek + time slot
    private UUID teacherId; // From user-service

    @Transient
    private TeacherDto teacher; // Fetched via WebClient
    // учители, които преподават дадения селектиран предмет

    // вземи учители за конкретен предмет

    // 3) вземи предмет
    @ManyToOne // do I need this ?
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
}
