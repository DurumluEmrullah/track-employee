package com.metamorfoz.track_employee.controller;

import com.metamorfoz.track_employee.controller.request.CreateEntranceRequestDto;
import com.metamorfoz.track_employee.controller.request.CreateExitRequestDto;
import com.metamorfoz.track_employee.controller.request.CreateVacationRequestDto;
import com.metamorfoz.track_employee.domain.EmployeeRecord;
import com.metamorfoz.track_employee.domain.Vacation;
import com.metamorfoz.track_employee.services.EmployeeRecordService;
import com.metamorfoz.track_employee.services.VacationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRecordService employeeRecordService;

    private final VacationService vacationService;

    @PostMapping("/create-vacation-request")
    public ResponseEntity<Vacation> createVacationRequest(@RequestBody CreateVacationRequestDto createVacationRequestDto){
        return ResponseEntity.ok(vacationService.createVacationRequest(createVacationRequestDto));
    }

    @PostMapping("/create-entrance-request")
    public ResponseEntity<EmployeeRecord> createEntranceRequest(@RequestBody CreateEntranceRequestDto createEntranceRequestDto){
        return ResponseEntity.ok(employeeRecordService.createEntranceRequest(createEntranceRequestDto));
    }

    @PostMapping("/create-exit-request")
    public ResponseEntity<EmployeeRecord> createExitRequest(@RequestBody CreateExitRequestDto createExitRequestDto){
        return ResponseEntity.ok(employeeRecordService.createExitRequest(createExitRequestDto));
    }
}
