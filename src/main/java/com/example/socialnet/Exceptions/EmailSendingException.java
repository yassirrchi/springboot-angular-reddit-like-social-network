package com.example.socialnet.Exceptions;

public class EmailSendingException extends RuntimeException{
    public EmailSendingException(String message){
        super(message);
    }
}
