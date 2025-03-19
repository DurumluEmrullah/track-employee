package com.metamorfoz.track_employee.controller.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateEmployeeRequestDto {

    private String name;
    private String lastname;
    private int managerId;
}
