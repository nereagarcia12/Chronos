package com.chronos.userservice.exceptions;

public class NoPresentUser extends RuntimeException{
    public NoPresentUser(){super("User is not present ");}
}
