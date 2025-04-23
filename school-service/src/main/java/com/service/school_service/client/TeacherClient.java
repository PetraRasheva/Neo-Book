package com.service.school_service.client;

import com.service.school_service.dto.TeacherDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class TeacherClient {

    private final WebClient webClient;

    public TeacherClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://api-gateway:8080").build(); // URL на user-service
    }

    public Mono<TeacherDto> getTeacherById(Long teacherId) {
        return webClient.get()
                .uri("/users/teachers/{id}", teacherId)
                .retrieve()
                .bodyToMono(TeacherDto.class);
    }
}
