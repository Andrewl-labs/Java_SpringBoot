package org.example.exceptions;

public class MemberNotFound extends RuntimeException {
    public MemberNotFound(String message) {
        super(message);
    }
}
