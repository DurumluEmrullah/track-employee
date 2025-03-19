package com.metamorfoz.track_employee.domain;

import com.metamorfoz.track_employee.domain.enums.ApprovalStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "T_VACATION")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vacation {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private int employeeId;

    private int managerId;

    private LocalDate startDate;

    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus status = ApprovalStatus.PENDING;
}
