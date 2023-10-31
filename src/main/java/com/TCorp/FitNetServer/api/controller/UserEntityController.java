package com.TCorp.FitNetServer.api.controller;

import com.TCorp.FitNetServer.api.model.UserEntity;
import com.TCorp.FitNetServer.api.service.UserEntityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "user/")
public class UserEntityController {

    private final UserEntityService userAccService;

    public UserEntityController(UserEntityService userAccService) {
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
}
