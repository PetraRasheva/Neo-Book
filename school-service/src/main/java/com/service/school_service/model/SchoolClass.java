package com.service.school_service.model;

import com.service.school_service.dto.StudentDto;
import com.service.school_service.dto.TeacherDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;

@Data
@Entity
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int gradeLevel;

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
    private HashSet<StudentDto> students;
}
