package com.metamorfoz.track_employee.dao;

import com.metamorfoz.track_employee.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleDao extends JpaRepository<UserRole,Integer> {
    Optional<UserRole> findByRoleLike(String role);
}
