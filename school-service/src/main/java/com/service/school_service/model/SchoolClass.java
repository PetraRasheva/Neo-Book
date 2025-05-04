package com.service.school_service.model;

import com.service.school_service.dto.StudentDto;
import com.service.school_service.dto.TeacherDto;
import com.service.school_service.enums.GradeLetter;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Entity
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int gradeLevel;

    @Column
    @Enumerated(EnumType.STRING)
    private GradeLetter letter;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @ManyToOne
    @JoinColumn(name = "speciality_id", nullable = false)
    private Speciality speciality;

    private Long teacherId; // Stored in DB

    @Transient
    private TeacherDto teacherDto;

    @Transient
    private Set<StudentDto> students;
}
