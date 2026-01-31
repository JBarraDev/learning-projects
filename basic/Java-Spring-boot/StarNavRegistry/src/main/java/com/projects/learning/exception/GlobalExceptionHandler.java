package com.projects.learning.exception;

import com.projects.learning.dto.ErrorMessageDTO;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Captura nuestra excepción personalizada (404)
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageDTO handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return new ErrorMessageDTO(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
    }

    // 2. Captura los errores de validación (@Valid) de los DTOs (400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageDTO handleValidationErrors(MethodArgumentNotValidException ex) {
        // Juntamos todos los mensajes de error de los campos en uno solo
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return new ErrorMessageDTO(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                "Error de validación: " + errors,
                "Los datos enviados no cumplen los requisitos galácticos."
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageDTO handleJsonErrors(HttpMessageNotReadableException ex, WebRequest request) {

        String message = "Error de formato en el JSON";

        Throwable cause = ex.getCause();
        while (cause != null) {
            if (cause instanceof IllegalArgumentException) {
                message = cause.getMessage();
                break;
            }
            cause = cause.getCause();
        }

        return new ErrorMessageDTO(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                message,
                request.getDescription(false)
        );
    }
}
