package com.TCorp.FitNetServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class FitNetServerApplication {

	public static void main(String[] args) {
		System.out.println("Starting FitNetServerApplication...");
		SpringApplication.run(FitNetServerApplication.class, args);
	}

	@GetMapping("/")
	public List<String> hello() {
		return List.of("Hello World! API is currently v1.");
	}
}
