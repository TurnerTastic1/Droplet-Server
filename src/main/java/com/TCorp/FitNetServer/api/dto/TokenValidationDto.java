package com.TCorp.FitNetServer.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * File: TokenValidationDto
 * Author: turnernaef
 * Date: 1/6/24
 * Description:
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenValidationDto {
    @NotNull(message = "Token cannot be null")
    @Size(min = 1, max = 1000, message = "Token must be between 1 and 1000 characters")
    private String token;
}
