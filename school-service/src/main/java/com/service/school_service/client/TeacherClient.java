package com.service.school_service.client;

import com.service.school_service.dto.TeacherDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
public class TeacherClient {

    private final WebClient webClient;

    public TeacherClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://api-gateway:8080").build(); // URL на user-service
    }

    public TeacherDto getTeacherById(UUID teacherId) {
        if ( teacherId == null )
            throw new IllegalArgumentException("teacherId must not be null");
        return webClient.get()
                .uri("/api/teachers/{id}", teacherId)
                .retrieve()
                .bodyToMono(TeacherDto.class)
                .block(); // This makes it synchronous
    }
}
