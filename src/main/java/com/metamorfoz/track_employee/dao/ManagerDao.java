package com.metamorfoz.track_employee.dao;

import com.metamorfoz.track_employee.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerDao extends JpaRepository<Manager,Integer> {
}
