package org.siit.springworkshop.controller;

import org.siit.springworkshop.exception.AgeException;
import org.siit.springworkshop.exception.DataNotFound;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AgeException.class, DataNotFound.class})
    public ResponseEntity<Object> handleAgeException(Exception ex, WebRequest webRequest) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        ex.getBindingResult().getAllErrors().stream()
                .filter(error -> error instanceof FieldError)
                .map(objectError -> (FieldError) objectError)
//                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)) // is doing the same thing as the below forEach()
                .forEach(objectError -> {
                    String fieldName = objectError.getField();
                    String errorMessage = objectError.getDefaultMessage();

                    validationErrors.put(fieldName, errorMessage);
                });

        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

}
