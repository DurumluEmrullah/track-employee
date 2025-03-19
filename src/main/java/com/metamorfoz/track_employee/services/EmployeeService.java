package com.metamorfoz.track_employee.services;

import com.metamorfoz.track_employee.controller.request.CreateEmployeeRequestDto;
import com.metamorfoz.track_employee.domain.Employee;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(CreateEmployeeRequestDto createEmployeeRequestDto);

    List<Integer> getEmployeeIdsByManagerId(int managerId);
}
