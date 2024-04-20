package com.example.WilsonBooksAPI.appError;

public class BadRequestException  extends RuntimeException  {
    public BadRequestException(String message) {
        super(message);
    }
}
