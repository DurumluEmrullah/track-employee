package com.metamorfoz.track_employee.services.impl;

import com.metamorfoz.track_employee.controller.request.CreateEmployeeRequestDto;
import com.metamorfoz.track_employee.dao.EmployeeDao;
import com.metamorfoz.track_employee.domain.Employee;
import com.metamorfoz.track_employee.domain.Manager;
import com.metamorfoz.track_employee.services.EmployeeService;
import com.metamorfoz.track_employee.services.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    private final ManagerService managerService;


    @Override
    public Employee createEmployee(CreateEmployeeRequestDto createEmployeeRequestDto) {

        return employeeDao.save(mapToEmployee(createEmployeeRequestDto
                ,managerService.getManagerById(createEmployeeRequestDto.getManagerId())));
    }

    private Employee mapToEmployee(CreateEmployeeRequestDto createEmployeeRequestDto,Manager manager){

        Employee employee = new Employee();
        employee.setName(createEmployeeRequestDto.getName());
        employee.setLastname(createEmployeeRequestDto.getLastname());
        employee.setManager(manager);
        return employee;
    }



    @Override
    public List<Integer> getEmployeeIdsByManagerId(int managerId) {
        return employeeDao.findEmployeeIdsByManagerId(managerId);
    }
}
