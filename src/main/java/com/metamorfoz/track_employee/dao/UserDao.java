package com.metamorfoz.track_employee.dao;

import com.metamorfoz.track_employee.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User,Integer> {
    boolean existsByUsername(String username);

    boolean existsByUserRoleList_RoleLike(String role);

    Optional<User> findByUsername(String username);
}
