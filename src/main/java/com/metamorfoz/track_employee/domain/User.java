package com.metamorfoz.track_employee.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Table(name = "T_USERS")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private int bindedUserId;

    private String username;

    private String password;

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private Set<UserRole> userRoleList;
}
