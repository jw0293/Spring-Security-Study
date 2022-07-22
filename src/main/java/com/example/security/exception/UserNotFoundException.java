package com.example.security.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String email){
        super(email + " NotFoundException");
    }
}
