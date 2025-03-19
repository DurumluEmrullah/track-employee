package com.metamorfoz.track_employee.common.controlleradvice;

import com.metamorfoz.track_employee.common.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage() + ". ";
            errors.put(fieldName, message);

        });

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmployeeAlreadyEnteredException.class)
    protected ResponseEntity<Object> employeeAlreadyEnteredExceptionHandler(WebRequest request,EmployeeAlreadyEnteredException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(EmployeeRecordNotFoundException.class)
    protected ResponseEntity<Object> employeeRecordNotFoundExceptionHandler(WebRequest request,EmployeeRecordNotFoundException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(ManagerNotExistException.class)
    protected ResponseEntity<Object> managerNotExistExceptionHandler(WebRequest request,ManagerNotExistException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(RoleNotFoundException.class)
    protected ResponseEntity<Object> roleNotFoundExceptionHandler(WebRequest request,RoleNotFoundException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(SuperManagerAlreadyExistException.class)
    protected ResponseEntity<Object> superManagerAlreadyExistExceptionHandler(WebRequest request,SuperManagerAlreadyExistException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(TokenException.class)
    protected ResponseEntity<Object> tokenExceptionHandler(WebRequest request,TokenException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(UsernameAlreadyExistException.class)
    protected ResponseEntity<Object> usernameAlreadyExistExceptionHandler(WebRequest request,UsernameAlreadyExistException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> userNotFoundExceptionHandler(WebRequest request,UserNotFoundException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(VacationNotFoundException.class)
    protected ResponseEntity<Object> vacationNotFoundExceptionHandler(WebRequest request,VacationNotFoundException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(WrongPasswordException.class)
    protected ResponseEntity<Object> wrongPasswordExceptionHandler(WebRequest request,WrongPasswordException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(EmployeeRecordTimeException.class)
    protected ResponseEntity<Object> employeeRecordTimeExceptionnHandler(WebRequest request,EmployeeRecordTimeException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
