package com.service.school_service.service;

import com.service.school_service.client.StudentClient;
import com.service.school_service.client.TeacherClient;
import com.service.school_service.dto.CreateSchoolClassDto;
import com.service.school_service.dto.SchoolClassDto;
import com.service.school_service.dto.StudentDto;
import com.service.school_service.dto.TeacherDto;
import com.service.school_service.mapper.SchoolClassMapper;
import com.service.school_service.model.SchoolClass;
import com.service.school_service.repository.SchoolClassRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SchoolClassServiceImpl implements SchoolClassService {
    private final SchoolClassRepository schoolClassRepository;
    private final TeacherClient teacherClient;
    private final StudentClient studentClient;

    private final SchoolClassMapper schoolClassMapper;

    public SchoolClassServiceImpl(SchoolClassRepository schoolClassRepository, TeacherClient teacherClient, StudentClient studentClient, SchoolClassMapper schoolClassMapper) {
        this.schoolClassRepository = schoolClassRepository;
        this.teacherClient = teacherClient;
        this.studentClient = studentClient;
        this.schoolClassMapper = schoolClassMapper;
    }

    @Override
    public Mono<SchoolClassDto> createSchoolClass(CreateSchoolClassDto schoolClassDto) {
        return Mono.fromCallable(() -> {
                SchoolClass schoolClass = schoolClassMapper.toEntity(schoolClassDto);
                SchoolClass schoolClassSaved = schoolClassRepository.save(schoolClass);
                return schoolClassMapper.toDto(schoolClassSaved);
                })
                .subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * Retrieves a {@link SchoolClass} by its ID, then asynchronously fetches and attaches
     * the associated {@link TeacherDto} and list of {@link StudentDto} objects.
     *
     * <p>This method performs the following steps:</p>
     * <ul>
     *   <li>Loads the school class from the database (synchronously via JPA).</li>
     *   <li>Fetches the assigned teacher and all students for the class asynchronously using WebClient.</li>
     *   <li>Combines the data into a fully populated {@link SchoolClass} object.</li>
     * </ul>
     *
     * @param classId the ID of the school class to retrieve
     * @return a {@link Mono} emitting the fully populated {@link SchoolClass}
     */
    @Override
    public Mono<SchoolClassDto> getSchoolClass(Long classId) {
        return Mono.fromCallable(() -> schoolClassRepository.findById(classId).orElseThrow())
                .subscribeOn(Schedulers.boundedElastic())
                // .flatMap - chain an asynchronous operation that returns another Mono.
                .flatMap(schoolClass ->
                        // .zip - combines two Monos and waits for both to complete. The result is a tuple (Tuple2) that contains both results.
                        Mono.zip(
                                teacherClient.getTeacherById(schoolClass.getTeacherId()),
                                studentClient.getStudentsByClassId(schoolClass.getId())
                        ).map(tuple -> {
                            schoolClass.setTeacherDto(tuple.getT1());
                            schoolClass.setStudents(tuple.getT2());
                            return schoolClass;
                        })
                )
                .map(schoolClassMapper::toDto);
    }


    /**
     * Retrieves all {@link SchoolClass} entries from the database.
     *
     * <p>This method performs the following steps:</p>
     * <ul>
     *   <li>Wraps the blocking JPA call {@code schoolClassRepository.findAll()} in a {@link Mono}.</li>
     *   <li>Converts the resulting {@link List} to a {@link Flux} stream using {@code flatMapMany}.</li>
     *   <li>Runs the blocking operation on a separate thread using {@link Schedulers#boundedElastic()}
     *       to avoid blocking the reactive pipeline.</li>
     * </ul>
     *
     * @return a {@link Flux} emitting all {@link SchoolClass} entities stored in the database
     */
    @Override
    public Flux<SchoolClassDto> getAllSchoolClasses() {
        return Mono.fromCallable(() -> schoolClassRepository.findAll())
                .subscribeOn(Schedulers.boundedElastic())
                .flatMapMany(Flux::fromIterable).map(schoolClassMapper::toDto);
    }

    @Override
    public Mono<Void> deleteSchoolClass(Long id) {
        return Mono.fromRunnable(() -> schoolClassRepository.deleteById(id))
                .subscribeOn(Schedulers.boundedElastic())
                .then(studentClient.removeSchoolClassReference(id)); // async call to user-service
    }

    /**
     * Updates a {@link SchoolClass} identified by the given ID with the provided data.
     *
     * <p>This method performs the following operations:</p>
     * <ul>
     *   <li>Loads the existing class from the database (blocking, wrapped in a reactive {@link Mono}).</li>
     *   <li>Checks if the teacher has changed and fetches the updated teacher data if needed.</li>
     *   <li>Determines changes in the student list, removes old class references from removed students,
     *       assigns the class ID to newly added students, and fetches the updated list of students.</li>
     *   <li>Updates the entity fields (name, gradeLevel, teacherId, students).</li>
     *   <li>Saves the updated class back to the database.</li>
     * </ul>
     *
     * @param id the ID of the school class to update
     * @param updatedClassDto the new data to update the school class with
     * @return a {@link Mono} emitting the updated {@link SchoolClassDto}
     */
    @Override
    public Mono<SchoolClassDto> updateSchoolClass(Long id, SchoolClassDto updatedClassDto) {
        return Mono.fromCallable(() -> schoolClassRepository.findById(id).orElseThrow(() -> new RuntimeException("School class not found with id: " + id)))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(existingClass -> {
                    SchoolClass updatedClass = schoolClassMapper.toEntity(updatedClassDto);
                    existingClass.setName(updatedClass.getName());
                    existingClass.setGradeLevel(updatedClass.getGradeLevel());

                    boolean teacherChanged = !existingClass.getTeacherId().equals(updatedClass.getTeacherId());

                    Set<Long> currStudentIds = existingClass.getStudents() == null ? Set.of() :
                            existingClass.getStudents().stream().map(StudentDto::id).collect(Collectors.toSet());

                    Set<Long> newStudentIds = updatedClass.getStudents() == null ? Set.of() :
                            updatedClass.getStudents().stream().map(StudentDto::id).collect(Collectors.toSet());

                    boolean studentsChanged = !currStudentIds.equals(newStudentIds);

                    Mono<TeacherDto> teacherMono;
                    if (teacherChanged) {
                        teacherMono = teacherClient.getTeacherById(updatedClass.getTeacherId());
                        existingClass.setTeacherId(updatedClass.getTeacherId());
                    } else {
                        teacherMono = Mono.just(existingClass.getTeacherDto());
                    }

                    Mono<HashSet<StudentDto>> studentsMono;
                    if (studentsChanged) {
                        // IDs to remove classId from
                        Set<Long> removedStudentIds = new HashSet<>(currStudentIds);
                        removedStudentIds.removeAll(newStudentIds);

                        // IDs to assign new classId
                        Set<Long> addedStudentIds = new HashSet<>(newStudentIds);
                        addedStudentIds.removeAll(currStudentIds);

                        // Call to studentClient to update removed students
                        Mono<Void> removeOldLinks = studentClient.removeClassIdFromStudents(new HashSet<>(removedStudentIds));

                        // Call to studentClient to assign new classId
                        Mono<Void> assignNewLinks = studentClient.assignClassIdToStudents(new HashSet<>(addedStudentIds), existingClass.getId());

                        // After updates, fetch the new list of students
                        studentsMono = Mono.when(removeOldLinks, assignNewLinks)
                                .then(studentClient.getStudentsByIds(new HashSet<>(newStudentIds)));
                    } else {
                        studentsMono = Mono.just(existingClass.getStudents());
                    }

                    return Mono.zip(teacherMono, studentsMono)
                            .map(tuple -> {
                                existingClass.setTeacherDto(tuple.getT1());
                                existingClass.setStudents(tuple.getT2());
                                return existingClass;
                            });
                })
                .flatMap(updated -> Mono.fromCallable(() -> schoolClassRepository.save(updated))
                        .subscribeOn(Schedulers.boundedElastic())).map(schoolClassMapper::toDto);
                }
}
