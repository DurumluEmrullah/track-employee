package com.metamorfoz.track_employee.controller;


import com.metamorfoz.track_employee.common.auth.JwtTokenProvider;
import com.metamorfoz.track_employee.controller.request.CreateManagerRequestDto;
import com.metamorfoz.track_employee.controller.request.GenerateMonthlyAttendanceReportRequestDto;
import com.metamorfoz.track_employee.controller.request.UpdateVacationRequestDto;
import com.metamorfoz.track_employee.controller.response.MonthlyAttendanceReportResponseDto;
import com.metamorfoz.track_employee.dao.ManagerDao;
import com.metamorfoz.track_employee.domain.Manager;
import com.metamorfoz.track_employee.domain.Vacation;
import com.metamorfoz.track_employee.services.EmployeeRecordService;
import com.metamorfoz.track_employee.services.ManagerService;
import com.metamorfoz.track_employee.services.VacationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/manager")
@RequiredArgsConstructor
public class ManagerController {


    private final VacationService vacationService;

    private final EmployeeRecordService employeeRecordService;

    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/get-waited-vacation-request")
    public ResponseEntity<List<Vacation>> getWaitedVacationRequest(HttpServletRequest httpServletRequest) {
        String managerId = jwtTokenProvider.getWorkerId(httpServletRequest.getHeaders("Authorization").nextElement());
        return ResponseEntity.ok(vacationService.getWaitedVacationRequestByManagerId(Integer.valueOf(managerId)));
    }

    @PostMapping("/update-vacation-request")
    public ResponseEntity<Vacation> updateVacationRequest(@RequestBody UpdateVacationRequestDto updateVacationRequestDto) {
        return ResponseEntity.ok(vacationService.updateVacation(updateVacationRequestDto));
    }

    @PostMapping("/generate-monthly-attendance-report")
    public ResponseEntity<List<MonthlyAttendanceReportResponseDto>> generateMonthlyAttendanceReport(@RequestBody GenerateMonthlyAttendanceReportRequestDto generateMonthlyAttendanceReportRequestDto) {

        return ResponseEntity.ok(employeeRecordService.generateMonthlyAttendanceReport(generateMonthlyAttendanceReportRequestDto));
    }

}
