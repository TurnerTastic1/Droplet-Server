package com.TCorp.FitNetServer.api.routes.auth;

import com.TCorp.FitNetServer.api.dto.AuthenticationDto;
import com.TCorp.FitNetServer.api.dto.RegisterDto;
import com.TCorp.FitNetServer.api.dto.TokenValidationDto;
import com.TCorp.FitNetServer.api.exception.CustomException;
import com.TCorp.FitNetServer.api.model.Role;
import com.TCorp.FitNetServer.api.model.UserEntity;
import com.TCorp.FitNetServer.api.repository.UserEntityRepository;
import com.TCorp.FitNetServer.api.security.JwtService;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.Serializable;
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

    public AuthenticationResponse register(RegisterDto registerDto) {
        List<String> errors = new ArrayList<>();

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
        newUserEntity.setRole(Role.USER);

        try {
            UserEntityRepo.save(newUserEntity);

            var jwtToken = jwtService.generateToken(newUserEntity);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .build();
        } catch (Exception e) {
            errors.add(e.toString());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to save user", errors);
        }
    }

    public AuthenticationResponse authenticate(AuthenticationDto authenticationDto) {
        List<String> errors = new ArrayList<>();

        var newUserEntity = UserEntityRepo.findUserEntityByEmailOrUsername(null, authenticationDto.getUsername())
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "User not found"));

        try {
            Authentication authRequest = UsernamePasswordAuthenticationToken.unauthenticated(authenticationDto.getUsername(), authenticationDto.getPassword());
            Authentication authentication = authenticationManager.authenticate(authRequest);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            var jwtToken = jwtService.generateToken(newUserEntity);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .build();
        } catch (Exception e) {
            errors.add(e.toString());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to authenticate user", errors);
        }
    }

    public Authentication getSecurityContextHolder() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication;
        }
        return null;
    }

    public UserEntity getUserEntityFromAuthentication() {
        Authentication authorizedUser = getSecurityContextHolder();
        return UserEntityRepo.findUserEntityByEmailOrUsername(null, authorizedUser.getName())
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "User not found"));
    }

    public Boolean checkExpiredToken(TokenValidationDto tokenValidationDto) {
        boolean expired;
        try {
            expired = jwtService.isTokenExpired(tokenValidationDto.getToken());
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to check token validation");
        }
        return expired;
    }
}
