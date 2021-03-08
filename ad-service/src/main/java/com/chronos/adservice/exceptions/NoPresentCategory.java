package com.chronos.adservice.exceptions;

public class NoPresentCategory extends RuntimeException{
    public NoPresentCategory() {
        super("Category is not present");
    }
}
