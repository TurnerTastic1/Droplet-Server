package com.TCorp.FitNetServer.api.model;

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
    private String username;
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
        this.username = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public UserAccount(String name,
                       String email,
                       String password,
                       String type) {
        this.username = name;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
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
                    ", name='" + username + '\'' +
                    ", email='" + email + '\'' +
                    ", type='" + type + '\'' +
                    '}';
    }
}
