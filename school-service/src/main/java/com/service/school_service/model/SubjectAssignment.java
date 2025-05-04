package com.service.school_service.model;

import com.service.school_service.dto.TeacherDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Data
@Entity
public class SubjectAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private DayOfWeek dayOfWeek; // Enum: MONDAY, TUESDAY, etc.

    private LocalDateTime startTime; // e.g., 08:30
    private LocalDateTime endTime;   // e.g., 09:15

    private Long teacherId; // From user-service

    @Transient
    private TeacherDto teacher; // Fetched via WebClient

    @OneToOne // do I need this ?
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;
}
