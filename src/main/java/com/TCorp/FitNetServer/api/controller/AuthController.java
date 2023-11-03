package com.TCorp.FitNetServer.api.controller;

import com.TCorp.FitNetServer.api.dto.AuthenticationDto;
import com.TCorp.FitNetServer.api.dto.LoginDto;
import com.TCorp.FitNetServer.api.dto.RegisterDto;
import com.TCorp.FitNetServer.api.model.UserEntity;
import com.TCorp.FitNetServer.api.response.AuthenticationResponse;
import com.TCorp.FitNetServer.api.service.AuthService;
import com.TCorp.FitNetServer.api.service.UserEntityService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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

    @PostMapping("/register-new-user")
    public ResponseEntity<Map<String, Object>> registerNewUser(@RequestBody RegisterDto registerDto) {
        return AuthService.registerNewUser(registerDto);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Map<String, Object>> authenticate(@RequestBody AuthenticationDto authenticationDto) {
        return AuthService.authenticate(authenticationDto);
    }

//    @PostMapping ("/login")
//    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginDto loginDto) {
//        return AuthService.login(loginDto);
//    }
}
