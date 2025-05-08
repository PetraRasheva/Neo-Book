package com.service.school_service.exception;

public class SchoolClassNotFoundException extends RuntimeException{
    public SchoolClassNotFoundException(Long id) {
        super("SchoolClass with ID " + id + " not found.");
    }
}
