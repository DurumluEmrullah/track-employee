package com.metamorfoz.track_employee.services.impl;

import com.metamorfoz.track_employee.common.exceptions.VacationNotFoundException;
import com.metamorfoz.track_employee.controller.request.CreateVacationRequestDto;
import com.metamorfoz.track_employee.controller.request.UpdateVacationRequestDto;
import com.metamorfoz.track_employee.dao.VacationDao;
import com.metamorfoz.track_employee.domain.Vacation;
import com.metamorfoz.track_employee.domain.enums.ApprovalStatus;
import com.metamorfoz.track_employee.services.VacationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VacationServiceImpl implements VacationService {

    private final VacationDao vacationDao;

    @Override
    public Vacation createVacationRequest(CreateVacationRequestDto createVacationRequestDto) {

        return vacationDao.save(mapToVacation(createVacationRequestDto));
    }

    private Vacation mapToVacation(CreateVacationRequestDto createVacationRequestDto){
        Vacation vacation = new Vacation();
        vacation.setEmployeeId(createVacationRequestDto.getEmployeeId());
        vacation.setStartDate(createVacationRequestDto.getStartDate());
        vacation.setEndDate(createVacationRequestDto.getEndDate());
        vacation.setManagerId(createVacationRequestDto.getManagerId());
        return vacation;
    }

    @Override
    public Vacation updateVacation(UpdateVacationRequestDto updateVacationRequestDto) {

        Vacation vacationRequest = vacationDao.findById(updateVacationRequestDto.getVacationId())
                .orElseThrow(()-> new VacationNotFoundException("Vacation not found id: " +updateVacationRequestDto.getVacationId()));

        vacationRequest.setStatus(updateVacationRequestDto.getStatus());

        return vacationDao.save(vacationRequest);
    }

    @Override
    public List<Vacation> getWaitedVacationRequestByManagerId(int managerId) {

        return vacationDao.findByManagerIdAndStatus(managerId,ApprovalStatus.PENDING);
    }
}
