package org.example.exceptions;

public class NoBookAvailableException extends RuntimeException {
    public NoBookAvailableException(String message) {
        super(message);
    }
}
