package com.metamorfoz.track_employee.services.impl;

import com.metamorfoz.track_employee.common.exceptions.RoleNotFoundException;
import com.metamorfoz.track_employee.dao.UserRoleDao;
import com.metamorfoz.track_employee.domain.UserRole;
import com.metamorfoz.track_employee.services.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleDao userRoleDao;

    @Override
    public UserRole getRoleByName(String roleName) {

        return userRoleDao.findByRoleLike(roleName)
                .orElseThrow(()-> new RoleNotFoundException("Role not Found"));

    }
}
