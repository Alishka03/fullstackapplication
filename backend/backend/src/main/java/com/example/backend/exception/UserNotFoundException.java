package com.example.backend.exception;

public class UserNotFoundException extends RuntimeException{


    public UserNotFoundException(Long id) {
        super("Not found with id :"+id);
    }
}

