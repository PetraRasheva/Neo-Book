package com.service.school_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle validation errors from @Validated
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Collect all validation error messages
        List<String> errors = ex.getBindingResult()
                .getFieldErrors() // Use getFieldErrors() to directly get FieldError objects
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        // Return a bad request response with the error messages
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Handle other exceptions like RuntimeException or custom exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleOtherExceptions(Exception ex) {
        // Log the exception details if needed
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
