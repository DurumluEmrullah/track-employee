package com.metamorfoz.track_employee.controller.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GenerateMonthlyAttendanceReportRequestDto {

    private int managerId;
    private LocalDate month;
}
