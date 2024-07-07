package com.tuppertech.microservices.quizService.exception;

public class ValidationException extends Exception{
    public ValidationException(String message){
        super(message);
    }
}
