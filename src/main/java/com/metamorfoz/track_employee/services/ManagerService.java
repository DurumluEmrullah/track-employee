package com.metamorfoz.track_employee.services;

import com.metamorfoz.track_employee.controller.request.CreateManagerRequestDto;
import com.metamorfoz.track_employee.controller.request.UpdateVacationRequestDto;
import com.metamorfoz.track_employee.domain.Manager;
import com.metamorfoz.track_employee.domain.Vacation;

import java.util.List;

public interface ManagerService {

    Manager createManager(CreateManagerRequestDto createManagerRequestDto);

    Manager getManagerById(int managerId);

}
