package com.example.demo.exception;

/**
 * URL not found exception
 * Thrown when the URL input has no matching entry in the database
 *
 */
public class UrlNotFoundException extends Exception{
    public UrlNotFoundException(String msg) {
        super(msg);
    }
}
