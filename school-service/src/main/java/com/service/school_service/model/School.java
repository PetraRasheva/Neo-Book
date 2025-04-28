package com.service.school_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
    private List<SchoolClass> classes;
}