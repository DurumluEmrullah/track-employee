package com.metamorfoz.track_employee.services;

import com.metamorfoz.track_employee.controller.request.CreateVacationRequestDto;
import com.metamorfoz.track_employee.controller.request.UpdateVacationRequestDto;
import com.metamorfoz.track_employee.domain.Vacation;

import java.util.List;

public interface VacationService {

    Vacation createVacationRequest(CreateVacationRequestDto createVacationRequestDto);

    Vacation updateVacation(UpdateVacationRequestDto updateVacationRequestDto);

    List<Vacation> getWaitedVacationRequestByManagerId(int managerId);


}
