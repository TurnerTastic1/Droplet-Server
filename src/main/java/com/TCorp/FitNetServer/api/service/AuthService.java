package com.TCorp.FitNetServer.api.service;

import com.TCorp.FitNetServer.api.dto.AuthenticationDto;
import com.TCorp.FitNetServer.api.dto.RegisterDto;
import com.TCorp.FitNetServer.api.exception.CustomException;
import com.TCorp.FitNetServer.api.model.Role;
import com.TCorp.FitNetServer.api.model.UserEntity;
import com.TCorp.FitNetServer.api.repository.UserEntityRepository;
import com.TCorp.FitNetServer.api.response.AuthenticationResponse;
import com.TCorp.FitNetServer.api.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * File: AuthService
 * Author: turnernaef
 * Date: 10/31/23
 * Description: This file contains the AuthService class, which is used to handle authentication requests.
 */

@Service
@RequiredArgsConstructor
public class AuthService {

    Logger logger = LoggerFactory.getLogger(AuthService.class);


    private final UserEntityRepository UserEntityRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<Map<String, Object>> registerNewUser(RegisterDto registerDto) {
        List<String> errors = new ArrayList<>();
        if (registerDto == null) {
            errors.add("Dto is required");
        }
        if (registerDto.getUsername() == null || registerDto.getUsername().isEmpty()) {
            errors.add("Username is required");
        }
        if (registerDto.getEmail() == null || registerDto.getEmail().isEmpty()) {
            errors.add("Email is required");
        }
        if (registerDto.getPassword() == null || registerDto.getPassword().isEmpty()) {
            errors.add("Password is required");
        }
        if (UserEntityRepo.findUserEntityByEmailOrUsername(registerDto.getEmail(), registerDto.getUsername()).isPresent()) {
            errors.add("Username or email already exists");
        }

        if (!errors.isEmpty()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Unable to save user", errors);
        }

        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setUsername(registerDto.getUsername());
        newUserEntity.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        newUserEntity.setEmail(registerDto.getEmail());

//        Optional<Role> role = roleRepository.findRoleByName("USER");
//        if (role.isEmpty()) {
//            throw new RuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to add user roles");
//        }

        newUserEntity.setRole(Role.USER);

        errors = new ArrayList<>();

        try {
            UserEntityRepo.save(newUserEntity);

            var jwtToken = jwtService.generateToken(newUserEntity);
            return ResponseEntity.ok(
                    Map.of(
                            "message", "User added successfully",
                            "token", AuthenticationResponse.builder()
                                    .token(jwtToken)
                                    .build()
                            )
            );
        } catch (Exception e) {
            errors.add(e.toString());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to save user", errors);
        }
    }

    public ResponseEntity<Map<String, Object>> authenticate(AuthenticationDto authenticationDto) {
//        logger.trace("Log level: TRACE");
//        logger.info("Log level: INFO");
//        logger.debug("Log level: DEBUG");
//        logger.error("Log level: ERROR");
//        logger.warn("Log level: WARN");
        List<String> errors = new ArrayList<>();
        if (authenticationDto == null) {
            errors.add("Dto is required");
        }
        if (authenticationDto.getUsername() == null || authenticationDto.getUsername().isEmpty()) {
            errors.add("Username is required");
        }
        if (authenticationDto.getPassword() == null || authenticationDto.getPassword().isEmpty()) {
            errors.add("Password is required");
        }

        if (!errors.isEmpty()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Unable to authenticate user", errors);
        }

        var newUserEntity = UserEntityRepo.findUserEntityByUsername(authenticationDto.getUsername())
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "User not found"));

        try {
            var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(newUserEntity.getUsername(), newUserEntity.getPassword())
            );
            logger.error("User authentication: " + authentication.toString());;
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.error("User authenticated successfully");

            var jwtToken = jwtService.generateToken(newUserEntity);
            return ResponseEntity.ok(
                    Map.of(
                            "message", "User authenticated successfully",
                            "token", AuthenticationResponse.builder()
                                    .token(jwtToken)
                                    .build()
                    )
            );
        } catch (Exception e) {
            e.printStackTrace();
            errors.add(e.toString());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to authenticate user", errors);
        }
    }

//    public ResponseEntity<Map<String, Object>> login(LoginDto loginDto) {
////        Authentication authentication = authenticationManager.authenticate(
////                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
////        );
////        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        return ResponseEntity.ok(Map.of("message", "User logged in successfully"));
//    }
}
