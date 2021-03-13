package com.chronos.adservice.exceptions;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException() {
        super("Category is not present");
    }
}
