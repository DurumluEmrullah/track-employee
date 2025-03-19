package com.metamorfoz.track_employee.controller.request;

import lombok.Data;

@Data
public class CreateUserRequestDto {

    private String name;
    private String lastname;
    private String username;
    private String password;
}
