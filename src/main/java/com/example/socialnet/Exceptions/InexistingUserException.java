package com.example.socialnet.Exceptions;

public class InexistingUserException extends RuntimeException{
    public InexistingUserException(String message){
        super(message);
    }
}