package com.TCorp.FitNetServer.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * File: AuthenticationResponse
 * Author: turnernaef
 * Date: 11/3/23
 * Description:
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
}
