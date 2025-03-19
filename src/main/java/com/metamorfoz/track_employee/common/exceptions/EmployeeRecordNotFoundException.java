package com.metamorfoz.track_employee.common.exceptions;

public class EmployeeRecordNotFoundException extends RuntimeException {
    public EmployeeRecordNotFoundException(String message) {
        super(message);
    }
}
