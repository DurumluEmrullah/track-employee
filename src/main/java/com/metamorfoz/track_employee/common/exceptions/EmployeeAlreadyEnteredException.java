package com.metamorfoz.track_employee.common.exceptions;

public class EmployeeAlreadyEnteredException extends RuntimeException {
    public EmployeeAlreadyEnteredException(String message) {
        super(message);
    }
}
