package com.chronos.userservice.exceptions;

public class EmailAlreadyRegisteredException extends RuntimeException{
    public EmailAlreadyRegisteredException(){super("Este email ya esta registrado. ");}
}
