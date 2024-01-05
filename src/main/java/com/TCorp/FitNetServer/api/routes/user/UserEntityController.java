package com.TCorp.FitNetServer.api.routes.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/FitNetServer/api/v1/user/")
public class UserEntityController {

    private final UserEntityService userAccService;

    public UserEntityController(UserEntityService userAccService) {
        this.userAccService = userAccService;
    }

    @GetMapping("get-all-users")
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        return userAccService.getAllUsers();
    }
}
