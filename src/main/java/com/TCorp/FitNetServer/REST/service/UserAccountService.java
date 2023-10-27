package com.TCorp.FitNetServer.REST.service;

import com.TCorp.FitNetServer.REST.model.UserAccount;
import com.TCorp.FitNetServer.REST.repository.UserAccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
            throw e;
           // return ResponseEntity.ok(Map.of("message", "Unable to fetch users"));
        }
    }

    public ResponseEntity<Map<String, Object>> registerNewUser() {
        return ResponseEntity.ok(Map.of("message", "User added successfully"));
    }
}
