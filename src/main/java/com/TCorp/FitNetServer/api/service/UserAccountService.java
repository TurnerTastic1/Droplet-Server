package com.TCorp.FitNetServer.api.service;

import com.TCorp.FitNetServer.api.exception.RuntimeException;
import com.TCorp.FitNetServer.api.model.UserAccount;
import com.TCorp.FitNetServer.api.repository.UserAccountRepository;
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

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
        if (newUser.getUsername() == null || newUser.getUsername().isEmpty()) {
            errors.add("Username is required");
        }
        if (newUser.getPassword() == null || newUser.getPassword().isEmpty()) {
            errors.add("Password is required");
        }

        Optional<UserAccount> userAccountOptional = UserAccRepo.findUserAccountByEmailOrUsername(newUser.getEmail(), newUser.getUsername());
        if (userAccountOptional.isPresent()) {
            errors.add("Email or name already exists");
        }

        if (!errors.isEmpty()) {
            throw new RuntimeException(HttpStatus.BAD_REQUEST, "Unable to save user", errors);
        }

        // Encode password
//        String encodedPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
//        newUser.setPassword(encodedPassword);

        try {
            UserAccRepo.save(newUser);
            System.out.println(newUser);
            return ResponseEntity.ok(Map.of("message", "User added successfully"));
        } catch (Exception e) {
            errors = new ArrayList<>();
            errors.add(e.toString());
            throw new RuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to save user", errors);
        }
    }
}
