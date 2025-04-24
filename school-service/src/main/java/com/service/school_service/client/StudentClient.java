package com.service.school_service.client;

import com.service.school_service.dto.StudentDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class StudentClient {

    private final WebClient webClient;

    public StudentClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://api-gateway:8080").build();
    }

    public Mono<HashSet<StudentDto>> getStudentsByClassId(Long classId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/users/students/")
                        .queryParam("classId", classId)
                        .build())
                .retrieve()
                .bodyToFlux(StudentDto.class)
                .collect(Collectors.toCollection(HashSet::new));
    }

    public Mono<Void> removeSchoolClassReference(Long schoolClassId) {
        return webClient.put()
                .uri("students/clear-class/{classId}", schoolClassId)
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Mono<Void> removeClassIdFromStudents(HashSet<Long> studentIds) {
        return webClient.put()
                .uri("students/clear-class")
                .bodyValue(studentIds)
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Mono<Void> assignClassIdToStudents(HashSet<Long> studentIds, Long schoolClassId) {
        return webClient.put()
                .uri("students/add-class/{id}" , schoolClassId)
                .bodyValue(studentIds)
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Mono<HashSet<StudentDto>> getStudentsByIds(HashSet<Long> studentIds) {
        // Build query params like ?ids=1&ids=2&ids=3
        return webClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path("students")
                                .queryParam("ids", studentIds.toArray())
                                .build()
                )
                .retrieve()
                .bodyToFlux(StudentDto.class) // Assuming the response is a JSON array of students
                .collect(Collectors.toCollection(HashSet::new));
    }
}
