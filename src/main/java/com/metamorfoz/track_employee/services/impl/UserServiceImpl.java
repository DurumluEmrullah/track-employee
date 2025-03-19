package com.metamorfoz.track_employee.services.impl;

import com.metamorfoz.track_employee.common.auth.JwtTokenProvider;
import com.metamorfoz.track_employee.common.exceptions.SuperManagerAlreadyExistException;
import com.metamorfoz.track_employee.common.exceptions.UserNotFoundException;
import com.metamorfoz.track_employee.common.exceptions.UsernameAlreadyExistException;
import com.metamorfoz.track_employee.common.exceptions.WrongPasswordException;
import com.metamorfoz.track_employee.controller.request.*;
import com.metamorfoz.track_employee.dao.UserDao;
import com.metamorfoz.track_employee.domain.Employee;
import com.metamorfoz.track_employee.domain.Manager;
import com.metamorfoz.track_employee.domain.User;
import com.metamorfoz.track_employee.domain.UserRole;
import com.metamorfoz.track_employee.services.EmployeeService;
import com.metamorfoz.track_employee.services.ManagerService;
import com.metamorfoz.track_employee.services.UserRoleService;
import com.metamorfoz.track_employee.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final ManagerService managerService;

    private final UserRoleService userRoleService;

    private final EmployeeService employeeService;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public User createSuperManager(CreateUserRequestDto createUserRequestDto) {

        boolean superManager = userDao.existsByUserRoleList_RoleLike("SUPER_MANAGER");

        if (superManager) {
            throw new SuperManagerAlreadyExistException("Super Manager Already Exist");
        }

        checkUsernameIsExist(createUserRequestDto.getUsername());

        CreateManagerRequestDto createManagerRequestDto = new CreateManagerRequestDto(createUserRequestDto.getName(), createUserRequestDto.getLastname());


        Manager manager = managerService.createManager(createManagerRequestDto);


        return userDao.save(createUser("SUPER_MANAGER",createUserRequestDto.getUsername(),
                createUserRequestDto.getPassword(),manager.getId()));
    }

    @Override
    public User createManager(CreateManagerTypeUserRequestDto createUserRequestDto) {

        checkUsernameIsExist(createUserRequestDto.getUsername());

        Manager manager = managerService.createManager(mapToCreateManagerRequestDto(createUserRequestDto));


        return userDao.save(createUser("MANAGER",createUserRequestDto.getUsername(),
                createUserRequestDto.getPassword(),manager.getId()));
    }

    private CreateManagerRequestDto mapToCreateManagerRequestDto(CreateManagerTypeUserRequestDto createUserRequestDto){
        return CreateManagerRequestDto.builder()
                .name(createUserRequestDto.getName())
                .lastname(createUserRequestDto.getLastname())
                .build();
    }

    private void checkUsernameIsExist(String username){

        if(userDao.existsByUsername(username)){
            throw new UsernameAlreadyExistException(username+ " already exist ");
        }
    }

    @Override
    public User createEmployee(CreateEmployeeTypeUserRequestDto createEmployeeTypeUserRequestDto) {


        checkUsernameIsExist(createEmployeeTypeUserRequestDto.getUsername());


        Employee employee = employeeService.createEmployee(mapToCreateEmployeeRequestDto(createEmployeeTypeUserRequestDto));

        return userDao.save(
                createUser("EMPLOYEE",createEmployeeTypeUserRequestDto.getUsername(),
                        createEmployeeTypeUserRequestDto.getPassword(),employee.getId()));
    }

    private CreateEmployeeRequestDto mapToCreateEmployeeRequestDto(CreateEmployeeTypeUserRequestDto createEmployeeTypeUserRequestDto){
        return CreateEmployeeRequestDto.builder()
                .name(createEmployeeTypeUserRequestDto.getName())
                .lastname(createEmployeeTypeUserRequestDto.getLastname())
                .managerId(createEmployeeTypeUserRequestDto.getManagerId())
                .build();
    }

    private User createUser(String roleName, String username, String password, int bindedUserId) {
        checkUsernameIsExist(username);

        UserRole role = userRoleService.getRoleByName(roleName);
        Set<UserRole> roleSet = Set.of(role);

        User user = new User();
        user.setBindedUserId(bindedUserId);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setUserRoleList(roleSet);

        return userDao.save(user);
    }


    @Override
    public String login(UserLoginRequestDto userLoginRequestDto) {
        User user = userDao.findByUsername(userLoginRequestDto.getUsername()).orElseThrow(() -> new UserNotFoundException("User Not Found !!"));

        if (!passwordEncoder.matches(userLoginRequestDto.getPassword(),
                user.getPassword())) {
            throw new WrongPasswordException("Wrong Password");
        }

        return jwtTokenProvider.generateToken(
                        user.getBindedUserId(),userLoginRequestDto.getUsername(),user.getUserRoleList());
    }
}
