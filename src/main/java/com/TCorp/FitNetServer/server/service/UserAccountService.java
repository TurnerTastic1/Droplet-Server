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
            throw new RuntimeException("Unable to fetch users from database", HttpStatus.EXPECTATION_FAILED);
           // return ResponseEntity.ok(Map.of("message", "Unable to fetch users"));
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
        return ResponseEntity.ok(Map.of("message", "User added successfully"));
    }
}
