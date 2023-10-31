package com.TCorp.FitNetServer.server.service;

import com.TCorp.FitNetServer.server.exception.RuntimeException;
import com.TCorp.FitNetServer.server.model.UserAccount;
import com.TCorp.FitNetServer.server.repository.UserAccountRepository;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserAccountService {

    private final UserAccountRepository UserAccRepo;

    public UserAccountService(UserAccountRepository UserAccRepo) {
        this.UserAccRepo = UserAccRepo;
    }

    public ResponseEntity<Map<String, Object>> getAllUsers() {
        try {
            List<UserAccount> UserAccounts = UserAccRepo.findAll();
            return ResponseEntity.ok(Map.of("message", "Users fetched successfully", "Users", UserAccounts));
        } catch (Exception e) {
            throw new RuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to fetch users from database");
        }
    }

    public ResponseEntity<Map<String, Object>> registerNewUser(UserAccount newUser) {
        List<String> errors = new ArrayList<>();
        if (newUser.getEmail() == null || newUser.getEmail().isEmpty()) {
            errors.add("Email is required");
        }
        if (newUser.getName() == null || newUser.getName().isEmpty()) {
            errors.add("Name is required");
        }

        Optional<UserAccount> userAccountOptional = UserAccRepo.findUserAccountByEmailOrName(newUser.getEmail(), newUser.getName());
        if (userAccountOptional.isPresent()) {
            errors.add("Email or name already exists");
        }

        if (!errors.isEmpty()) {
            throw new RuntimeException(HttpStatus.BAD_REQUEST, "Unable to save user", errors);
        }

        try {
            UserAccRepo.save(newUser);
        } catch (Exception e) {
            errors = new ArrayList<>();
            errors.add(e.toString());
            throw new RuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to save user", errors);
        }


        return ResponseEntity.ok(Map.of("message", "User added successfully"));
    }
}
