package com.workforpica.task.controller.payload.lobby;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse {

    public enum responses{
        SUCCESS,
        NOT_FOUND,
        FAIL,
        VALIDATION_ERROR
    }

    String status;
    String message;
    List<String> validationErrors;

    public GenericResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public GenericResponse(String status, String message, List<String> validationErrors) {
        this.status = status;
        this.message = message;
        this.validationErrors = validationErrors;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public String toString() {
        try {
            return (new ObjectMapper()).writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException var2) {
            var2.printStackTrace();
            return null;
        }
    }
}
