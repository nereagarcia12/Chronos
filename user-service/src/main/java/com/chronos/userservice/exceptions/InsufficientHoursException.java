package com.chronos.userservice.exceptions;

public class InsufficientHoursException extends RuntimeException{
    public InsufficientHoursException() {
        super("No tienes horas suficientes para esta solicitud");
    }
}
