package com.TCorp.FitNetServer;

import com.TCorp.FitNetServer.api.response.ResponseGlobal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class FitNetServerApplication {

	public static void main(String[] args) {
		System.out.println("Starting FitNetServerApplication...");
		SpringApplication.run(FitNetServerApplication.class, args);
	}

	@GetMapping("/FitNetServer/api/v1/root-status")
	public ResponseEntity<ResponseGlobal> hello() {
		Map<String, Object> apiData = Map.of( "version", "V1");
		return ResponseEntity.ok(
				ResponseGlobal.builder()
						.message("Successfully retrieved root status")
						.success(true)
						.responsesMultiple(apiData)
						.build()
		);
	}
}
