package com.TCorp.FitNetServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FitNetServerApplication {

	public static void main(String[] args) {
		System.out.println("Starting FitNetServerApplication...");
		SpringApplication.run(FitNetServerApplication.class, args);
	}

}
