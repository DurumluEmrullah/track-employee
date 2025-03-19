package com.metamorfoz.track_employee.dao;

import com.metamorfoz.track_employee.domain.Vacation;
import com.metamorfoz.track_employee.domain.enums.ApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacationDao extends JpaRepository<Vacation,Integer> {
    List<Vacation> findByManagerId(int managerId);

    List<Vacation> findByManagerIdAndStatus(int managerId, ApprovalStatus status);
}
