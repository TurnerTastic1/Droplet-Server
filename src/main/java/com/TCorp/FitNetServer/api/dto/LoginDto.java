package com.TCorp.FitNetServer.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * File: LoginDto
 * Author: turnernaef
 * Date: 11/1/23
 * Description: This class is used to transfer login information from the client to the server.
 */

@Data
public class LoginDto {
    @NotNull(message = "Username cannot be null")
    @Size(min = 1, max = 20, message = "Username must be between 1 and 20 characters")
    private String username;

    @NotNull(message = "Password cannot be null")
    @Size(min = 6, max = 50, message = "Password must be between 6 and 50 characters")
    private String password;

    @NotNull(message = "Email cannot be null")
    @Size(min = 1, max = 50, message = "Email must be between 1 and 50 characters")
    private String email;
}
