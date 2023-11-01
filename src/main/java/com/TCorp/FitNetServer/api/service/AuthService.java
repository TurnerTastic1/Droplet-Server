package com.TCorp.FitNetServer.api.service;

import com.TCorp.FitNetServer.api.dto.RegisterDto;
import com.TCorp.FitNetServer.api.exception.RuntimeException;
import com.TCorp.FitNetServer.api.model.Role;
import com.TCorp.FitNetServer.api.model.UserEntity;
import com.TCorp.FitNetServer.api.repository.RoleRepository;
import com.TCorp.FitNetServer.api.repository.UserEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(UserEntityRepository UserEntityRepo, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.UserEntityRepo = UserEntityRepo;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    public ResponseEntity<Map<String, Object>> registerNewUser(RegisterDto registerDto) {
        List<String> errors = new ArrayList<>();
        if (registerDto == null) {
            errors.add("Dto is required");
        }
        if (registerDto.getUsername() == null || registerDto.getUsername().isEmpty()) {
            errors.add("Username is required");
        }
        if (registerDto.getPassword() == null || registerDto.getPassword().isEmpty()) {
            errors.add("Password is required");
        }
        if (UserEntityRepo.findUserEntityByUsername(registerDto.getUsername()).isPresent()) {
            errors.add("Username already exists");
        }

//        Optional<UserEntity> userAccountOptional = UserEntityRepo.findUserEntityByEmailOrUsername(newUser.getEmail(), newUser.getUsername());
//        if (userAccountOptional.isPresent()) {
//            errors.add("Email or name already exists");
//        }

        if (!errors.isEmpty()) {
            throw new RuntimeException(HttpStatus.BAD_REQUEST, "Unable to save user", errors);
        }

        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setUsername(registerDto.getUsername());
        newUserEntity.setPassword(bCryptPasswordEncoder.encode(registerDto.getPassword()));
        newUserEntity.setEmail(registerDto.getEmail());

        Optional<Role> roles = roleRepository.findRoleByName("USER");
        if (roles.isEmpty()) {
            throw new RuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to add user roles");
        }

        newUserEntity.setRoles(Collections.singletonList(roles.get()));

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
}
