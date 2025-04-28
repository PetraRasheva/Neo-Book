//package com.service.school_service.service;
//
//import com.service.school_service.model.School;
//import com.service.school_service.repository.SchoolRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.util.ArrayList;
//
//public class SchoolServiceImplementationTest {
//    private SchoolRepository schoolRepository;
//
//    private SchoolServiceImplementation schoolServiceImplementation;
//    @BeforeEach
//    void setUp() {
//        schoolRepository = Mockito.mock(SchoolRepository.class);
//        schoolServiceImplementation = new SchoolServiceImplementation(schoolRepository);
//    }
//
//    @Test
//    void createSchool_ShouldSaveAndReturnSchool() {
//        //Given
//        School school = new School();
//        school.setName("Test School");
//        school.setAddress("Test Address");
//    }
//}
