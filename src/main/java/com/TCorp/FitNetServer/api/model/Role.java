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
public enum Role {
    USER,
    ADMIN
}
