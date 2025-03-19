package com.metamorfoz.track_employee.services;

import com.metamorfoz.track_employee.domain.UserRole;

public interface UserRoleService {

    UserRole getRoleByName(String roleName);
}
