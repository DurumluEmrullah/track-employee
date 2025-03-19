package com.metamorfoz.track_employee.controller.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CreateExitRequestDto {

    private int employeeId;
    private LocalDate day;
    private LocalTime leaveTime;
}
