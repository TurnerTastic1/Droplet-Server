package com.TCorp.FitNetServer.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * File: Roles
 * Author: turnernaef
 * Date: 10/31/23
 * Description:
 */
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
}
