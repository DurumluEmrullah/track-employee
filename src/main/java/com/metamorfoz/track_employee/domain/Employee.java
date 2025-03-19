package com.metamorfoz.track_employee.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_EMPLOYEE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private String name;

    private String lastname;


    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private Manager manager;

}
