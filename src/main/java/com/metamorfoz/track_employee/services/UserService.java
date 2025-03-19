package com.metamorfoz.track_employee.services;

import com.metamorfoz.track_employee.controller.request.CreateEmployeeTypeUserRequestDto;
import com.metamorfoz.track_employee.controller.request.CreateManagerTypeUserRequestDto;
import com.metamorfoz.track_employee.controller.request.CreateUserRequestDto;
import com.metamorfoz.track_employee.controller.request.UserLoginRequestDto;
import com.metamorfoz.track_employee.domain.User;

public interface UserService {


    User createSuperManager(CreateUserRequestDto createUserRequestDto);

    User createManager(CreateManagerTypeUserRequestDto createUserRequestDto);

    User createEmployee(CreateEmployeeTypeUserRequestDto createUserRequestDto);

    String login(UserLoginRequestDto userLoginRequestDto);
}
