package com.TCorp.FitNetServer.api.routes.auth;

import com.TCorp.FitNetServer.api.dto.AuthenticationDto;
import com.TCorp.FitNetServer.api.dto.RegisterDto;
import com.TCorp.FitNetServer.api.response.ResponseGlobal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * File: AuthController
 * Author: turnernaef
 * Date: 10/31/23
 * Description:
 */

@RestController
@RequestMapping(path = "/FitNetServer/api/v1/auth")
public class AuthController {

    private final AuthService AuthService;

    public AuthController(AuthService AuthService) {
        this.AuthService = AuthService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseGlobal> register(@RequestBody RegisterDto registerDto) {
        AuthenticationResponse serviceResponse = AuthService.register(registerDto);

        return ResponseEntity.ok(
                ResponseGlobal.builder()
                        .code(200)
                        .message("Successfully registered new user")
                        .status(true)
                        .data(Map.of("auth", serviceResponse))
                        .build()
        );
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseGlobal> authenticate(@RequestBody AuthenticationDto authenticationDto) {
        AuthenticationResponse serviceResponse = AuthService.authenticate(authenticationDto);

        return ResponseEntity.ok(
                ResponseGlobal.builder()
                        .code(200)
                        .message("Successfully authenticated user")
                        .status(true)
                        .data(Map.of("auth", serviceResponse))
                        .build()
        );
    }

//    @PostMapping ("/login")
//    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginDto loginDto) {
//        return AuthService.login(loginDto);
//    }
}
