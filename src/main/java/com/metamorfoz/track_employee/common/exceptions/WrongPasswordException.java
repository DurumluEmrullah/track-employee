package com.metamorfoz.track_employee.common.exceptions;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(String message) {
        super(message);
    }
}
