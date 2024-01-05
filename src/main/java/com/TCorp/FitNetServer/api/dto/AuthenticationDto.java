package com.TCorp.FitNetServer.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * File: AuthenticationDto
 * Author: turnernaef
 * Date: 11/3/23
 * Description:
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDto {
    @NotNull(message = "Username cannot be null")
    @Size(min = 1, max = 20, message = "Username must be between 1 and 20 characters")
    private String username;

    @NotNull(message = "Password cannot be null")
    @Size(min = 6, max = 50, message = "Password must be between 6 and 50 characters")
    private String password;
}
