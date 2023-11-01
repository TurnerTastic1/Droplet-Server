package com.TCorp.FitNetServer.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * File: Role
 * Author: turnernaef
 * Date: 10/31/23
 * Description: This class is used to create a table in the database that will hold the roles of the users.
 */
@Getter
@Setter
@Entity
@Table(name = "roles")
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public Role(String type) {
        this.name = type;
    }
}
