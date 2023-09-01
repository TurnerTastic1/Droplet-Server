package com.TCorp.FitNetServer.REST.account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "user/")
public class UserController {

    @GetMapping()
    public List<String> hello() {
        return List.of("Hello World from user controller!");
    }
}
