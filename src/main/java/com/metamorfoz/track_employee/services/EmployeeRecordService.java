package com.metamorfoz.track_employee.services;

import com.metamorfoz.track_employee.controller.request.CreateEntranceRequestDto;
import com.metamorfoz.track_employee.controller.request.CreateExitRequestDto;
import com.metamorfoz.track_employee.controller.request.GenerateMonthlyAttendanceReportRequestDto;
import com.metamorfoz.track_employee.controller.response.MonthlyAttendanceReportResponseDto;
import com.metamorfoz.track_employee.domain.EmployeeRecord;

import java.util.List;

public interface EmployeeRecordService {

    EmployeeRecord createEntranceRequest(CreateEntranceRequestDto createEntranceRequestDto);

    EmployeeRecord createExitRequest(CreateExitRequestDto createExitRequestDto);

    List<MonthlyAttendanceReportResponseDto> generateMonthlyAttendanceReport(GenerateMonthlyAttendanceReportRequestDto generateMonthlyAttendanceReportRequestDto);


}
