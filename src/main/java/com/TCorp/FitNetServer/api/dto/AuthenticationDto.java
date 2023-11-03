package com.TCorp.FitNetServer.api.dto;

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
    private String username;
    private String password;
}
