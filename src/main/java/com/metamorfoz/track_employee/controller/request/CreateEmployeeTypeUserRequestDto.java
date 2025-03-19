package com.metamorfoz.track_employee.controller.request;

import lombok.Data;

@Data
public class CreateEmployeeTypeUserRequestDto extends CreateUserRequestDto{
    private int managerId;
}
