package com.metamorfoz.track_employee.controller;

import com.metamorfoz.track_employee.controller.request.*;
import com.metamorfoz.track_employee.domain.User;
import com.metamorfoz.track_employee.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;


    @PostMapping("/create-super-manager")
    public ResponseEntity<User> createSuperManager(@RequestBody CreateUserRequestDto createUserRequestDto){
        return ResponseEntity.ok(userService.createSuperManager(createUserRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequestDto userLoginRequestDto){
        return ResponseEntity.ok(userService.login(userLoginRequestDto));
    }

    @PostMapping("/create-manager")
    public ResponseEntity<User> createManager(@RequestBody @Valid CreateManagerTypeUserRequestDto createManagerTypeUserRequestDto){
        return ResponseEntity.ok(userService.createManager(createManagerTypeUserRequestDto));
    }

    @PostMapping("/create-employee")
    public ResponseEntity<User> createEmployee(@RequestBody @Valid CreateEmployeeTypeUserRequestDto createEmployeeTypeUserRequestDto){
        return ResponseEntity.ok(userService.createEmployee(createEmployeeTypeUserRequestDto));
    }


}
