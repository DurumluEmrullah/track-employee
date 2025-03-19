package com.metamorfoz.track_employee.services.impl;

import com.metamorfoz.track_employee.common.exceptions.EmployeeAlreadyEnteredException;
import com.metamorfoz.track_employee.common.exceptions.EmployeeRecordNotFoundException;
import com.metamorfoz.track_employee.common.exceptions.EmployeeRecordTimeException;
import com.metamorfoz.track_employee.controller.request.CreateEntranceRequestDto;
import com.metamorfoz.track_employee.controller.request.CreateExitRequestDto;
import com.metamorfoz.track_employee.controller.request.GenerateMonthlyAttendanceReportRequestDto;
import com.metamorfoz.track_employee.controller.response.MonthlyAttendanceReportResponseDto;
import com.metamorfoz.track_employee.dao.EmployeeRecordDao;
import com.metamorfoz.track_employee.domain.EmployeeRecord;
import com.metamorfoz.track_employee.dto.MonthlyAttendanceReportDto;
import com.metamorfoz.track_employee.services.EmployeeRecordService;
import com.metamorfoz.track_employee.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeRecordServiceImpl implements EmployeeRecordService {

    private final EmployeeRecordDao employeeRecordDao;

    private final EmployeeService employeeService;


    @Override
    public EmployeeRecord createEntranceRequest(CreateEntranceRequestDto createEntranceRequestDto) {

        Optional<EmployeeRecord> employeeRecord = employeeRecordDao.findByEmployeeIdAndLeaveTimeNullAndDay(createEntranceRequestDto.getEmployeeId(), createEntranceRequestDto.getDay());

        employeeRecord.ifPresent(val -> {
            throw new EmployeeAlreadyEnteredException("Employee Already entered at company" + createEntranceRequestDto.getEmployeeId());
        });


        return employeeRecordDao.save(mapToEmployeeRecordFromCreateEntranceRequestDto(createEntranceRequestDto));
    }

    private EmployeeRecord mapToEmployeeRecordFromCreateEntranceRequestDto(CreateEntranceRequestDto createEntranceRequestDto){
        EmployeeRecord employeeRecord = new EmployeeRecord();

        employeeRecord.setEmployeeId(createEntranceRequestDto.getEmployeeId());
        employeeRecord.setDay(createEntranceRequestDto.getDay());
        employeeRecord.setArrivalTime(createEntranceRequestDto.getArrivalTime());
        return employeeRecord;
    }

    @Override
    public EmployeeRecord createExitRequest(CreateExitRequestDto createExitRequestDto) {
        EmployeeRecord employeeRecord = employeeRecordDao.findByEmployeeIdAndLeaveTimeNullAndDay(
                createExitRequestDto.getEmployeeId(), createExitRequestDto.getDay())
                .orElseThrow(() -> new EmployeeRecordNotFoundException("You must log in to log out of the company."));

        if(!employeeRecord.getArrivalTime().isBefore(createExitRequestDto.getLeaveTime())){
            throw new EmployeeRecordTimeException("leave time must be after arrival time");
        }

        employeeRecord.setLeaveTime(createExitRequestDto.getLeaveTime());
        return employeeRecordDao.save(employeeRecord);
    }


    @Override
    public List<MonthlyAttendanceReportResponseDto> generateMonthlyAttendanceReport(GenerateMonthlyAttendanceReportRequestDto generateMonthlyAttendanceReportRequestDto) {

        List<MonthlyAttendanceReportDto> monthlyAttendanceReports = employeeRecordDao
                .generateMonthlyAttendanceReport(generateMonthlyAttendanceReportRequestDto.getMonth().getMonthValue(),
                        employeeService.getEmployeeIdsByManagerId(generateMonthlyAttendanceReportRequestDto.getManagerId()));

        return monthlyAttendanceReports.stream()
                .map(item ->mapToResponseDto(item,generateMonthlyAttendanceReportRequestDto)).toList();
    }


    private MonthlyAttendanceReportResponseDto mapToResponseDto(MonthlyAttendanceReportDto item, GenerateMonthlyAttendanceReportRequestDto requestDto) {
        return MonthlyAttendanceReportResponseDto.builder()
                .employee(item.getEmployeeName())
                .totalHoursWorked(formatDuration(item.getTotalDuration()))
                .month(requestDto.getMonth().getMonth().name())
                .build();
    }

    private String formatDuration(long seconds) {
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }
}
