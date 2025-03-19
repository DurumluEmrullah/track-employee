package com.metamorfoz.track_employee.common.exceptions;

public class SuperManagerAlreadyExistException extends RuntimeException {
    public SuperManagerAlreadyExistException(String message) {
        super(message);
    }
}
