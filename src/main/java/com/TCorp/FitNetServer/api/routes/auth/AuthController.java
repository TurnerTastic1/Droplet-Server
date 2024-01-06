package com.TCorp.FitNetServer.api.routes.auth;

import com.TCorp.FitNetServer.api.dto.AuthenticationDto;
import com.TCorp.FitNetServer.api.dto.RegisterDto;
import com.TCorp.FitNetServer.api.dto.TokenValidationDto;
import com.TCorp.FitNetServer.api.response.ResponseGlobal;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * File: AuthController
 * Author: turnernaef
 * Date: 10/31/23
 * Description: This file contains the AuthController class, which is used to handle authentication requests.
 */

@RestController
@RequestMapping(path = "/FitNetServer/api/v1/auth")
public class AuthController {

    private final AuthService AuthService;

    public AuthController(AuthService AuthService) {
        this.AuthService = AuthService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseGlobal> register(@Valid @RequestBody RegisterDto registerDto) {
        AuthenticationResponse serviceResponse = AuthService.register(registerDto);

        return ResponseEntity.ok(
                ResponseGlobal.builder()
                        .code(200)
                        .message("Successfully registered new user")
                        .status(true)
                        .timestamp(System.currentTimeMillis())
                        .data(Map.of("auth", serviceResponse))
                        .build()
        );
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseGlobal> authenticate(@Valid @RequestBody AuthenticationDto authenticationDto) {
        AuthenticationResponse serviceResponse = AuthService.authenticate(authenticationDto);

        return ResponseEntity.ok(
                ResponseGlobal.builder()
                        .code(200)
                        .message("Successfully authenticated user")
                        .status(true)
                        .timestamp(System.currentTimeMillis())
                        .data(Map.of("auth", serviceResponse))
                        .build()
        );
    }

    @GetMapping("/check-expired-token")
    public ResponseEntity<ResponseGlobal> checkExpiredToken(@Valid @RequestBody TokenValidationDto tokenValidationDto) {
        Boolean serviceResponse = AuthService.checkExpiredToken(tokenValidationDto);

        return ResponseEntity.ok(
                ResponseGlobal.builder()
                        .code(200)
                        .message("Successfully checked experied token")
                        .status(true)
                        .timestamp(System.currentTimeMillis())
                        .data(Map.of("expired", serviceResponse))
                        .build()
        );
    }


}
