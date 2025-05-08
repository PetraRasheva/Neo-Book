package com.service.school_service.client;

import com.service.school_service.dto.StudentDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentClient {

    private final WebClient webClient;

    public StudentClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://api-gateway:8080").build();
    }

    public Set<StudentDto> getStudentsByClassId(Long classId) {
        if (classId == null)
            throw new IllegalArgumentException("classId must not be null");

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/students/")
                        .queryParam("classId", classId)
                        .build())
                .retrieve()
                .bodyToFlux(StudentDto.class)
                .collect(Collectors.toCollection(HashSet::new))
                .block();
    }

    public void removeSchoolClassReference(Long schoolClassId) {
        if (schoolClassId == null)
            throw new IllegalArgumentException("schoolClassId must not be null");

        webClient.put()
                .uri("students/clear-class/{classId}", schoolClassId)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public void removeClassIdFromStudents(Set<UUID> studentIds) {
        if (studentIds == null || studentIds.isEmpty())
            throw new IllegalArgumentException("studentIds must not be null or empty");

        webClient.put()
                .uri("students/clear-class")
                .bodyValue(studentIds)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public Set<StudentDto> getStudentsByIds(Set<UUID> studentIds) {
        if (studentIds == null || studentIds.isEmpty())
            throw new IllegalArgumentException("studentIds must not be null or empty");

        List<StudentDto> students = webClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path("students")
                                .queryParam("ids", studentIds.toArray())
                                .build()
                )
                .retrieve()
                .bodyToFlux(StudentDto.class)
                .collectList()
                .block(); // blocking

        return new HashSet<>(students);
    }

    public StudentDto getStudentById(UUID studentId) {
        if (studentId == null) throw new IllegalArgumentException("studentId must not be null");

        return webClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/api/students/{id}")
                                .queryParam("id", studentId)
                                .build()
                )
            .retrieve()
                .bodyToMono(StudentDto.class)
                .block();
    }

    public void assignStudentToClass(UUID studentId, Long classId) {
        if (studentId == null)
            throw new IllegalArgumentException("studentIds must not be null or empty");
        if (classId == null)
            throw new IllegalArgumentException("schoolClassId must not be null");

        webClient.put()
                .uri("students/add-class/{id}" , classId)
                .bodyValue(studentId)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public void unassignStudentToClass(UUID studentId, Long classId) {
        if (studentId == null)
            throw new IllegalArgumentException("studentIds must not be null or empty");
        if (classId == null)
            throw new IllegalArgumentException("schoolClassId must not be null");

        webClient.put()
                .uri("students/remove-class/{id}" , classId)
                .bodyValue(studentId)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
