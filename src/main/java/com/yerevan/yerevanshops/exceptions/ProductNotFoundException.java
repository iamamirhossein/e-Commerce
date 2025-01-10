package com.yerevan.yerevanshops.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message, String details) {
        super(message + " " + details);
    }
}
