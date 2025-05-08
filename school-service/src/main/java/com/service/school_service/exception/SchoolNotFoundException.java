package com.service.school_service.exception;

public class SchoolNotFoundException extends RuntimeException {
    public SchoolNotFoundException(Long id) {
        super("School with ID " + id + " not found.");
    }
}
