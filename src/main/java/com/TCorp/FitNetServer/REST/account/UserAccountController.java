package com.TCorp.FitNetServer.REST.account;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
    public ResponseEntity<Map<String, Object>> geAlltUsers() {
        return userAccService.getAllUsers();
    }

    @PostMapping("register-new-user")
    public ResponseEntity<Map<String, Object>> registerNewUser() {
        return userAccService.registerNewUser();
    }
}
