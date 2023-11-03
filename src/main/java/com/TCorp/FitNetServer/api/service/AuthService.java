package com.TCorp.FitNetServer.api.service;

import com.TCorp.FitNetServer.api.dto.LoginDto;
import com.TCorp.FitNetServer.api.dto.RegisterDto;
import com.TCorp.FitNetServer.api.exception.RuntimeException;
import com.TCorp.FitNetServer.api.model.Role;
import com.TCorp.FitNetServer.api.model.UserEntity;
//import com.TCorp.FitNetServer.api.repository.RoleRepository;
import com.TCorp.FitNetServer.api.repository.UserEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * File: AuthService
 * Author: turnernaef
 * Date: 10/31/23
 * Description: This file contains the AuthService class, which is used to handle authentication requests.
 */

@Service
public class AuthService {

    private final UserEntityRepository UserEntityRepo;
//    private final RoleRepository roleRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//    private final AuthenticationManager authenticationManager;

    public AuthService(UserEntityRepository UserEntityRepo) {
        this.UserEntityRepo = UserEntityRepo;
//        this.roleRepository = roleRepository;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//        this.authenticationManager = authenticationManager;
    }
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
            throw new RuntimeException(HttpStatus.BAD_REQUEST, "Unable to save user", errors);
        }

        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setUsername(registerDto.getUsername());
        newUserEntity.setPassword(registerDto.getPassword());
        newUserEntity.setEmail(registerDto.getEmail());

//        Optional<Role> role = roleRepository.findRoleByName("USER");
//        if (role.isEmpty()) {
//            throw new RuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to add user roles");
//        }

        newUserEntity.setRole(Role.USER);

        errors = new ArrayList<>();

        try {
            UserEntityRepo.save(newUserEntity);
           // System.out.println(newUser);
            return ResponseEntity.ok(Map.of("message", "User added successfully"));
        } catch (Exception e) {
            errors.add(e.toString());
            throw new RuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to save user", errors);
        }
    }

    public ResponseEntity<Map<String, Object>> login(LoginDto loginDto) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok(Map.of("message", "User logged in successfully"));
    }
}
