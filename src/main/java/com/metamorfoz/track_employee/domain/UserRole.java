package com.metamorfoz.track_employee.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@Entity
@Table(name = "T_USER_ROLES")
public class UserRole implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;


    private String  role;

    @Override
    public String getAuthority() {
        return role;
    }
}
