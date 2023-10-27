package com.TCorp.FitNetServer.server.model;

import jakarta.persistence.*;

@Entity
@Table
public class UserAccount {
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
    private String name;
    private String email;
    private String password;
    private String type;

    public UserAccount() {
    }

    public UserAccount(Long id,
                       String name,
                       String email,
                       String password,
                       String type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public UserAccount(String name,
                       String email,
                       String password,
                       String type) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", type='" + type + '\'' +
                    '}';
    }


    public static class Exercises {
    }
}
