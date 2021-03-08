package com.chronos.userservice.exceptions;

public class InsufficientHoursException extends RuntimeException{
    public InsufficientHoursException() {
        super("No allowed. Insufficient hours");
    }
}
