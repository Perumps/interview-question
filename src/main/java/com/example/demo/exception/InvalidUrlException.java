package com.example.demo.exception;

/**
 * Invalid URL exception
 * Thrown when the URL input is missing or invalid
 *
 */
public class InvalidUrlException extends Exception {
    public InvalidUrlException(String msg) {
        super(msg);
    }
}
