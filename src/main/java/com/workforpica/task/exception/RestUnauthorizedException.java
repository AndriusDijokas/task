package com.workforpica.task.exception;


public class RestUnauthorizedException extends RuntimeException {

    public RestUnauthorizedException(String message){
        super(message);
    }

    public RestUnauthorizedException(String message, Throwable throwable){
        super(message, throwable);
    }
}
