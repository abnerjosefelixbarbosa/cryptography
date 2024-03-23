package com.backend.java.backendjava.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDetails> handleException(RuntimeException e, HttpServletRequest request) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(LocalDateTime.now(), 400, e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(400).body(exceptionDetails);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleException(EntityNotFoundException e, HttpServletRequest request) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(LocalDateTime.now(), 400, e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(404).body(exceptionDetails);
    }

    @ExceptionHandler(NoSuchAlgorithmException.class)
    public ResponseEntity<ExceptionDetails> handleException(NoSuchAlgorithmException e, HttpServletRequest request) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(LocalDateTime.now(), 400, e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(400).body(exceptionDetails);
    }

}
