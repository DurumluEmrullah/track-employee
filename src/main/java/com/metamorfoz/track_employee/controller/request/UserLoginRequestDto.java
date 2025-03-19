package com.metamorfoz.track_employee.controller.request;

import lombok.Data;

@Data
public class UserLoginRequestDto {

    private String username;
    private String password;

}
