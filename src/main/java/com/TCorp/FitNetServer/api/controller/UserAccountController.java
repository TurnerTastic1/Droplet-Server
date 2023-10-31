package com.TCorp.FitNetServer.api.controller;

import com.TCorp.FitNetServer.api.model.UserAccount;
import com.TCorp.FitNetServer.api.service.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "user/")
public class UserAccountController {

    private final UserAccountService userAccService;

    public UserAccountController(UserAccountService userAccService) {
        this.userAccService = userAccService;
    }

    @GetMapping("health-check")
    public ResponseEntity<Map<String, Object>> hello() {
        return ResponseEntity.ok(Map.of("message", "Hello World from user controller!"));
    }

    @GetMapping("get-all-users")
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        return userAccService.getAllUsers();
    }

    @PostMapping("register-new-user")
    public ResponseEntity<Map<String, Object>> registerNewUser(@RequestBody UserAccount newUser) {
        return userAccService.registerNewUser(newUser);
    }
}
