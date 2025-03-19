package com.metamorfoz.track_employee.common.exceptions;

public class ManagerNotExistException extends RuntimeException {
    public ManagerNotExistException(String message) {
        super(message);
    }
}
