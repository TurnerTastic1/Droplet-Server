package com.TCorp.FitNetServer.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * File: UserEntity
 * Author: turnernaef
 * Date: 10/31/23
 * Description: This class is used to create a table in the database that will hold the users and a separate table that will hold the roles of the users.
 */

@Getter
@Setter
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    @SequenceGenerator(
            name = "user_account_sequence",
            sequenceName = "user_account_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator =  "user_account_sequence"
    )

    private Long id;
    private String username;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id"
            )
    )
    private List<Role> roles = new ArrayList<>();
}
