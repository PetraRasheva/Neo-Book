package com.service.school_service.model;

import com.service.school_service.dto.StudentDto;
import com.service.school_service.dto.TeacherDto;
import com.service.school_service.enums.GradeLetter;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    private UUID teacherId; // Stored in DB

    //TODO: import as an external lib
    @Transient
    private TeacherDto teacherDto;

    @Transient
    private Set<StudentDto> students = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule; // should not update this

    public void assignStudent(StudentDto students) {
        this.students.add(students);
    }

    public void unassignStudent(StudentDto students) {
        this.students.remove(students);
    }
}
