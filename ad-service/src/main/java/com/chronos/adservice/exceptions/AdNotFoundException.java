package com.chronos.adservice.exceptions;

public class AdNotFoundException extends RuntimeException{
    public AdNotFoundException() {
        super("Ad is not present");
    }
}
