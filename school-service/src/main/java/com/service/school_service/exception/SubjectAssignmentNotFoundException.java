package com.service.school_service.exception;

public class SubjectAssignmentNotFoundException extends RuntimeException {
    public SubjectAssignmentNotFoundException(Long id) {
        super("Subject Assignment Not Found: " + id);
    }
}
