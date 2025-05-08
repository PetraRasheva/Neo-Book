package com.service.school_service.exception;

public class SubjectNotFoundException extends RuntimeException {
    public SubjectNotFoundException(Long id) {
        super("Subject with ID " + id + " not found.");
    }
}
