package com.metamorfoz.track_employee.controller.request;

import lombok.Data;

import java.time.LocalDate;
@Data
public class CreateVacationRequestDto {

    private int employeeId;
    private int managerId;
    private LocalDate startDate;
    private LocalDate endDate;

}
