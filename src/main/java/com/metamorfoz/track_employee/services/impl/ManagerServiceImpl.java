package com.metamorfoz.track_employee.services.impl;

import com.metamorfoz.track_employee.common.exceptions.ManagerNotExistException;
import com.metamorfoz.track_employee.controller.request.CreateManagerRequestDto;
import com.metamorfoz.track_employee.dao.ManagerDao;
import com.metamorfoz.track_employee.domain.Manager;
import com.metamorfoz.track_employee.services.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final ManagerDao managerDao;


    @Override
    public Manager createManager(CreateManagerRequestDto createManagerRequestDto) {
        return managerDao.save(mapToManager(createManagerRequestDto));
    }

    private Manager mapToManager(CreateManagerRequestDto createManagerRequestDto){

        Manager manager = new Manager();
        manager.setName(createManagerRequestDto.getName());
        manager.setLastname(createManagerRequestDto.getLastname());
        return manager;
    }

    @Override
    public Manager getManagerById(int managerId) {
        return managerDao.findById(managerId)
                .orElseThrow(()-> new ManagerNotExistException("Manager Not Exist"));
    }

}
