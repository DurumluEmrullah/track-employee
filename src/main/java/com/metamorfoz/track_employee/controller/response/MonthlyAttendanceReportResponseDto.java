package com.metamorfoz.track_employee.controller.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

public class MonthlyAttendanceReportResponseDto {

    private String month;
    private String employee;
    private String totalHoursWorked;
}
