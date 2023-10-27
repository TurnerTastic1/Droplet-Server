package com.TCorp.FitNetServer.REST.account;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserAccountService {

    private final UserAccountRepository UserAccRepo;

    public UserAccountService(UserAccountRepository UserAccRepo) {
        this.UserAccRepo = UserAccRepo;
    }

    public ResponseEntity<Map<String, Object>> getAllUsers() {
        return null;
    }

    public ResponseEntity<Map<String, Object>> registerNewUser() {
        return ResponseEntity.ok(Map.of("message", "User added successfully"));
    }
}
