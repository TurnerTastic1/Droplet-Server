package com.TCorp.FitNetServer.api.dto;

import lombok.Data;

/**
 * File: LoginDto
 * Author: turnernaef
 * Date: 11/1/23
 * Description: This class is used to transfer login information from the client to the server.
 */

@Data
public class LoginDto {
    private String username;
    private String password;
    private String email;
}
