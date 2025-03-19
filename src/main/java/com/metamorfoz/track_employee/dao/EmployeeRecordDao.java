package com.metamorfoz.track_employee.dao;

import com.metamorfoz.track_employee.domain.EmployeeRecord;
import com.metamorfoz.track_employee.dto.MonthlyAttendanceReportDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeRecordDao extends JpaRepository<EmployeeRecord,Integer> {

    Optional<EmployeeRecord> findByEmployeeIdAndLeaveTimeNullAndDay(int employeeId, LocalDate day);

    @Query(value = """
        SELECT e.name AS employeeName,
               SUM(TIMESTAMPDIFF(SECOND, er.arrival_time, er.leave_time)) AS totalDuration
        FROM t_employee_record er
        JOIN t_employee e ON er.employee_id = e.id
        WHERE MONTH(er.day) = :month and er.employee_id in(:employee_id_list)
        GROUP BY er.employee_id
        ORDER BY totalDuration DESC
    """, nativeQuery = true)
    List<MonthlyAttendanceReportDto> generateMonthlyAttendanceReport(@Param("month") int month, @Param("employee_id_list") List<Integer> employeeIdList);
}
