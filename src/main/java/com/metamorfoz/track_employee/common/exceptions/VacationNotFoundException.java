package com.metamorfoz.track_employee.common.exceptions;

public class VacationNotFoundException extends RuntimeException {
    public VacationNotFoundException(String message) {
        super(message);
    }
}
