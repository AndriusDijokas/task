package com.workforpica.task.exception;

import com.workforpica.task.controller.payload.lobby.GenericResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {


    private final Logger log = LogManager.getLogger();

    @ExceptionHandler(value
            = {ConstraintViolationException.class})
    protected ResponseEntity<GenericResponse> handleConstraintViolationException(ConstraintViolationException ex) {

        GenericResponse genericResponse = new GenericResponse(
                HttpStatus.BAD_REQUEST.name(),
                GenericResponse.responses.VALIDATION_ERROR.name(),
                ex.getConstraintViolations().stream().map(cv -> cv.getMessage()).collect(Collectors.toList()));

        log.warn("Resolved: {}",genericResponse.toString(),ex.fillInStackTrace());
        return new ResponseEntity<GenericResponse>(genericResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value
            = {MethodArgumentNotValidException.class})
    protected ResponseEntity<GenericResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        GenericResponse genericResponse = new GenericResponse(
                HttpStatus.BAD_REQUEST.name(),
                GenericResponse.responses.VALIDATION_ERROR.name(),
                ex.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList()));
        log.warn("Resolved: {}",genericResponse.toString(),ex.fillInStackTrace());
        return new ResponseEntity<GenericResponse>(genericResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value
            = {HttpMessageNotReadableException.class})
    protected ResponseEntity<GenericResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {

        GenericResponse genericResponse = new GenericResponse(
                HttpStatus.BAD_REQUEST.name(),
                ex.getMessage());

        log.warn("Resolved: {}",genericResponse.toString(),ex.fillInStackTrace());
        return new ResponseEntity<GenericResponse>(genericResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value
            = {BadRequestException.class})
    protected ResponseEntity<GenericResponse> handleBadRequestException(BadRequestException ex) {

        GenericResponse genericResponse = new GenericResponse(
                HttpStatus.BAD_REQUEST.name(),
                ex.getMessage());

        log.warn("Resolved: {}",genericResponse.toString(),ex.fillInStackTrace());
        return new ResponseEntity<GenericResponse>(genericResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value
            = {OAuth2AuthenticationProcessingException.class})
    protected ResponseEntity<GenericResponse> handleHttpOAuth2AuthenticationProcessingException(OAuth2AuthenticationProcessingException ex) {

        GenericResponse genericResponse = new GenericResponse(
                HttpStatus.UNAUTHORIZED.name(),
                ex.getMessage());

        log.warn("Resolved: {}",genericResponse.toString(),ex.fillInStackTrace());
        return new ResponseEntity<GenericResponse>(genericResponse, HttpStatus.UNAUTHORIZED);
    }


}
