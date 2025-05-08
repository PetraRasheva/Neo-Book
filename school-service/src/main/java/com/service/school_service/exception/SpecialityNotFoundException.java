package com.service.school_service.exception;

public class SpecialityNotFoundException extends RuntimeException {
    public SpecialityNotFoundException(Long id) {
        super("Speciality with ID " + id + " not found.");
    }
}
