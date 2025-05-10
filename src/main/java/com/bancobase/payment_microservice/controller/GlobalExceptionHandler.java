package com.bancobase.payment_microservice.controller;

import com.bancobase.payment_microservice.dto.Resultado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String errores = fieldErrors.stream().map(fiel -> fiel.getDefaultMessage()).collect(Collectors.joining(", "));
        return ResponseEntity.badRequest().body(Resultado.failedResult(errores, HttpStatus.BAD_REQUEST.toString()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleValidationErrors(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(Resultado.failedResult(
                "No es un valor valido: "+ ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED.toString()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleValidationErrors(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity.badRequest().body(Resultado.failedResult(
                ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED.toString()));
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<?> handleValidationErrors(NumberFormatException ex) {
        return ResponseEntity.badRequest().body(Resultado.failedResult(
                ex.getMessage(), HttpStatus.BAD_REQUEST.toString()));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> handleValidationErrors(NoResourceFoundException ex) {
        return ResponseEntity.badRequest().body(Resultado.failedResult(
                ex.getMessage(), HttpStatus.BAD_REQUEST.toString()));
    }
}
