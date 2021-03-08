package com.chronos.adservice.exceptions;

public class NoAdPresent extends RuntimeException{
    public NoAdPresent() {
        super("Ad is not present");
    }
}
