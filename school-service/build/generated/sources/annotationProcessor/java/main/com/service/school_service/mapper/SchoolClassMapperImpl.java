package com.service.school_service.mapper;

import com.service.school_service.dto.CreateSchoolClassDto;
import com.service.school_service.dto.SchoolClassDto;
import com.service.school_service.dto.StudentDto;
import com.service.school_service.dto.TeacherDto;
import com.service.school_service.model.School;
import com.service.school_service.model.SchoolClass;
import com.service.school_service.model.Speciality;
import java.util.HashSet;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-01T10:45:11+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 17.0.14 (Amazon.com Inc.)"
)
@Component
public class SchoolClassMapperImpl implements SchoolClassMapper {

    @Override
    public SchoolClass toEntity(SchoolClassDto schoolClassDto) {
        if ( schoolClassDto == null ) {
            return null;
        }

        SchoolClass schoolClass = new SchoolClass();

        schoolClass.setSchool( idToSchool( schoolClassDto.schoolId() ) );
        schoolClass.setSpeciality( idToSpeciality( schoolClassDto.specialityId() ) );
        schoolClass.setId( schoolClassDto.id() );
        schoolClass.setName( schoolClassDto.name() );
        schoolClass.setGradeLevel( schoolClassDto.gradeLevel() );
        schoolClass.setTeacherId( schoolClassDto.teacherId() );

        return schoolClass;
    }

    @Override
    public SchoolClass toEntity(CreateSchoolClassDto createSchoolClassDto) {
        if ( createSchoolClassDto == null ) {
            return null;
        }

        SchoolClass schoolClass = new SchoolClass();

        schoolClass.setSchool( idToSchool( createSchoolClassDto.schoolId() ) );
        schoolClass.setSpeciality( idToSpeciality( createSchoolClassDto.specialityId() ) );
        schoolClass.setName( createSchoolClassDto.name() );
        schoolClass.setGradeLevel( createSchoolClassDto.gradeLevel() );
        schoolClass.setTeacherId( createSchoolClassDto.teacherId() );

        return schoolClass;
    }

    @Override
    public SchoolClassDto toDto(SchoolClass schoolClass) {
        if ( schoolClass == null ) {
            return null;
        }

        Long schoolId = null;
        Long specialityId = null;
        Long id = null;
        String name = null;
        int gradeLevel = 0;
        Long teacherId = null;
        TeacherDto teacherDto = null;
        HashSet<StudentDto> students = null;

        schoolId = schoolClassSchoolId( schoolClass );
        specialityId = schoolClassSpecialityId( schoolClass );
        id = schoolClass.getId();
        name = schoolClass.getName();
        gradeLevel = schoolClass.getGradeLevel();
        teacherId = schoolClass.getTeacherId();
        teacherDto = schoolClass.getTeacherDto();
        HashSet<StudentDto> hashSet = schoolClass.getStudents();
        if ( hashSet != null ) {
            students = new HashSet<StudentDto>( hashSet );
        }

        SchoolClassDto schoolClassDto = new SchoolClassDto( id, name, gradeLevel, schoolId, specialityId, teacherId, teacherDto, students );

        return schoolClassDto;
    }

    private Long schoolClassSchoolId(SchoolClass schoolClass) {
        if ( schoolClass == null ) {
            return null;
        }
        School school = schoolClass.getSchool();
        if ( school == null ) {
            return null;
        }
        Long id = school.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long schoolClassSpecialityId(SchoolClass schoolClass) {
        if ( schoolClass == null ) {
            return null;
        }
        Speciality speciality = schoolClass.getSpeciality();
        if ( speciality == null ) {
            return null;
        }
        Long id = speciality.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
