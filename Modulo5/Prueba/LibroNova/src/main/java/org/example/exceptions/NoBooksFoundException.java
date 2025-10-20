package org.example.exceptions;

public class NoBooksFoundException extends RuntimeException {
    public NoBooksFoundException(String message) {
        super(message);
    }
}
