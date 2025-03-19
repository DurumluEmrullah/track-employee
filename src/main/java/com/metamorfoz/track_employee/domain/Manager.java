package com.metamorfoz.track_employee.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "T_MANAGER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private String name;

    private String lastname;
}
