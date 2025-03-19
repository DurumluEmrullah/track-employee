package com.metamorfoz.track_employee.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_EMPLOYEE_RECORD")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private int employeeId;

    private LocalDate day;

    private LocalTime arrivalTime;

    private LocalTime leaveTime;


}
