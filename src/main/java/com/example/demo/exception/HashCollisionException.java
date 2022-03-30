package com.example.demo.exception;

/**
 * Hash collision exception
 * Thrown when a probable hash collision situation is detected
 *
 */
public class HashCollisionException extends Exception {
    public HashCollisionException(String msg) {
        super(msg);
    }
}
