package org.example.service.exceptions;

public class SalonNotFoundException extends Exception{
    public SalonNotFoundException(String message) {
        super(message);
    }
}
