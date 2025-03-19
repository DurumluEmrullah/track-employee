package com.metamorfoz.track_employee.dao;

import com.metamorfoz.track_employee.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeDao extends JpaRepository<Employee,Integer> {


    @Query("SELECT e.id FROM Employee e WHERE e.manager.id = :managerId")
    List<Integer> findEmployeeIdsByManagerId(@Param("managerId") int managerId);
}
